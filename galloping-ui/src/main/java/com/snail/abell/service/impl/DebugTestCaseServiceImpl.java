package com.snail.abell.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.snail.abell.base.Result;
import com.snail.abell.config.*;
import com.snail.abell.dao.TStepUiNewMapper;
import com.snail.abell.elementTypeHandler.*;
import com.snail.abell.entity.*;
import com.snail.abell.exception.BizException;
import com.snail.abell.service.*;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.snail.abell.base.ResultCode.*;

/**
 * @author Abell
 * @date 2022/12/11
 */
@Service
public class DebugTestCaseServiceImpl implements DebugTestCaseService {

    @Resource
    private TTestcaseUiNewService testcaseUiNewService;
    @Resource
    private TStepUiNewMapper stepUiNewMapper;
    @Resource
    private TPageElementService PageElementService;
    @Resource
    private StepUiLogService stepUiLogService;
    @Resource
    private TStepUiNewService stepUiNewService;
    @Resource
    private ScreenshotsHandle screenshotsHandle;
    @Resource
    private CaseStepService caseStepService;
    @Resource
    private CaseParamService caseParamService;
    @Resource
    private BrowserHandle browserHandle;
    @Resource
    private KeyboardHandle keyboardHandle;
    @Resource
    private CookerHandle cookerHandle;
    @Resource
    private FileHandle fileHandle;
    @Resource
    private JavaScriptHandle javaScriptHandle;

    public Logger log = LogManager.getLogger("TestCaseStep");

    /**
     * @param context 存储浏览器类型及用例ID
     * @return 执行用例结果
     * @throws Exception 异常
     */
    @Override
    public Result runTestCase(Map<String, String> context) throws Exception {

        String browserType = context.get("browser");
        String caseId = context.get("caseId");
        DriverFactory.initDriver(browserType);
        WebDriver driver = DriverFactory.getDriver();

        if (driver == null) {

            throw new BizException(DRIVER_EXIST_ERROR);

        }
        TTestcaseUiNew testcaseUiNew = testcaseUiNewService.lambdaQuery().eq(TTestcaseUiNew::getCaseId, caseId).one();
        List<CaseStep> caseStepList = caseStepService.lambdaQuery().eq(CaseStep::getCaseId, caseId).list();
        List<Integer> stepIds = caseStepList.stream().map(CaseStep::getStepId).collect(Collectors.toList());
        List<TStepUiNew> stepList = stepUiNewMapper.selectBatchIds(stepIds);
        List<TStepUiNew> sortsList = stepList.stream().sorted(Comparator.comparing(TStepUiNew::getSort)).collect(Collectors.toList());
        StringBuilder stepLogMessage = new StringBuilder();
        //获取用例内部参数
        log.info("执行测试用例:[{}]", testcaseUiNew.getName());
        stepLogMessage.append("执行测试用例:  [" + testcaseUiNew.getName() + "]\r\n");
        log.info("获取测试步骤");
        stepLogMessage.append("获取测试步骤");
        log.info("打开浏览器：{}，设置隐式等待{}秒", browserType, 5);
        stepLogMessage.append("打开浏览器：{" + browserType + "}，设置隐式等待{5}秒\r\n");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        for (TStepUiNew stepUiNew : sortsList) {

            Result stepResult = null;
            Integer status;
            String imgUrl = null;

            if (stepUiNew.isEnable()) {

                log.info("执行步骤" + stepUiNew.getName() + "---执行第【" + stepUiNew.getSort() + "】步");
                stepLogMessage.append("执行步骤:  " + stepUiNew.getName() + "\r\n执行第【" + stepUiNew.getSort() + "】步\r\n");

                try {

                    stepResult = isExcBusiness(stepUiNew, context, driver, stepLogMessage);
                    status = 0;
                    log.info("执行结果: " + stepResult.getMessage());

                } catch (Exception e) {


                    stepResult = new Result<>(FAIL_STEP, e);
                    String screenshotName = stepUiNew.getName() + ".png";
                    status = 1;
                    imgUrl =  screenshotsHandle.screenShot(driver,"testcase",screenshotName);
                    driver.quit();
                    saveExcStepLogs(stepUiNew, stepResult, status,imgUrl);
                    return stepResult;
                }

            } else {

                log.info("忽略执行步骤" + stepUiNew.getName() + "---执行第【" + stepUiNew.getSort() + "】步");
                stepLogMessage.append("忽略执行步骤" + stepUiNew.getName() + "---执行第【" + stepUiNew.getSort() + "】步\r\n");
                status = 2;
                saveExcStepLogs(stepUiNew, stepResult, status,imgUrl);
                stepResult = new Result<>(SKIP_STEP, stepUiNew.getName());
            }

            saveExcStepLogs(stepUiNew, stepResult, status,imgUrl);
        }
        driver.quit();
        return Result.success("测试用例执行成功!");
    }

    /**
     * @param stepUiNew 步骤
     * @param context   存储浏览器类型及用例ID
     * @param driver    驱动
     * @return 返回执行结果
     * @throws Exception 反射异常
     * @deprecated 执行具体的测试步骤，按照操作类型做具体业务判断
     */
    @Override
    public Result isExcBusiness(TStepUiNew stepUiNew, Map<String, String> context, WebDriver driver, StringBuilder stepLogMessage) throws Exception {

        String optionsData = getCaseVar(stepUiNew, context);

        log.info("获取用例局部变量" + stepUiNew.getOptionData(), ": 获取的值为" + optionsData);

        stepLogMessage.append("获取用例局部变量:  ").append(stepUiNew.getOptionData()).append("\r\n获取的值为:  ").append(optionsData).append("\r\n");

        TPageElement selected = new TPageElement();

        if (null != stepUiNew.getElementId()) {

            selected = PageElementService.lambdaQuery().eq(TPageElement::getId, stepUiNew.getElementId()).one();
            log.info("获取元素" + selected.getElementName(), ": 获取的值为" + selected.getByValue() + "{}**定位方式为: " + selected.getByType());
            stepLogMessage.append("获取元素: ").append(selected.getElementName())
                    .append("\r\n获取的值为: ").append(selected.getByValue())
                    .append("{}**定位方式为: ").append(selected.getByType()).append("\r\n");

        }

        SelectorStrategy strategy = AnnotationSelectorStrategyFactory.getSelectorStrategy(SelectorType.ID);

        log.info("执行操作步骤" + stepUiNew.getActionType(), ":步骤名称" + stepUiNew.getAction());

        stepLogMessage.append("执行操作步骤").append(stepUiNew.getActionType()).append("\r\n步骤名称: [").append(stepUiNew.getAction()).append("]\r\n");

        if (stepUiNew.getActionType().trim().equals(ActionType.SCENTYPE.message())) {

            context.put("caseId", optionsData);
            Result excBusinessResult = runTestCase(context);

            if (!excBusinessResult.isSuccess()) {
                throw new BizException(SCEN_EXIST_ERROR);
            }

        } else if (stepUiNew.getActionType().trim().equals(ActionType.BROWER.message())) {

            Method method = ReflectUtil.getMethodByNameIgnoreCase(BrowserHandle.class, getMethodName(stepUiNew.getAction()));

            if (StringUtils.isBlank(selected.getByValue()) && StringUtils.isNotBlank(optionsData)) {

                method.invoke(browserHandle,optionsData, driver);
                strategy.sleep(stepUiNew.getWaite());

            } else {

                method.invoke(browserHandle,driver);

                strategy.sleep(stepUiNew.getWaite());
            }
        } else if (stepUiNew.getActionType().trim().equals(ActionType.COOKERTYPE.message())) {

            Method method = ReflectUtil.getMethodByNameIgnoreCase(CookerHandle.class, getMethodName(stepUiNew.getAction()));

            if (StringUtils.isNotBlank(optionsData) && "添加cookies信息".equals(stepUiNew.getAction().trim())) {

                //解析cooker数据抽离对应的name和value���
                Map<String, Object> cookerObject = JSON.parseObject(optionsData);
                method.invoke(cookerHandle,cookerObject, driver);
                strategy.sleep(stepUiNew.getWaite());

            } else if (StringUtils.isNotBlank(optionsData) && "根据cookie名称删除cookie".equals(stepUiNew.getAction().trim())) {

                method.invoke(cookerHandle,optionsData, driver);
                strategy.sleep(stepUiNew.getWaite());

            } else {

                method.invoke(cookerHandle,driver);
                strategy.sleep(stepUiNew.getWaite());

            }

        } else if (stepUiNew.getActionType().trim().equals(ActionType.ELEMENTTYPE.message())) {

            SelectorStrategy strategySE = AnnotationSelectorStrategyFactory.getSelectorStrategy(SelectorType.valueOf(selected.getByType()));

            Method method = ReflectUtil.getMethodByNameIgnoreCase(strategySE.getClass(), getMethodName(stepUiNew.getAction()));

            if (null != stepUiNew.getCounts()) {

                method.invoke(strategySE.getClass().newInstance(),selected.getByValue(), stepUiNew.getCounts(), driver);
                strategy.sleep(stepUiNew.getWaite());

            } else if (StringUtils.isNotBlank(optionsData)) {

                method.invoke(strategySE.getClass().newInstance(),selected.getByValue(), optionsData, driver);
                strategy.sleep(stepUiNew.getWaite());

            } else {

                method.invoke(strategySE.getClass().newInstance(),selected.getByValue(), driver);
                strategy.sleep(stepUiNew.getWaite());

            }
        } else if (stepUiNew.getActionType().trim().equals(ActionType.JS.message())) {

            SelectorStrategy strategySource = AnnotationSelectorStrategyFactory.getSelectorStrategy(SelectorType.valueOf(selected.getByType()));

            Method methodElement = ReflectUtil.getMethodByNameIgnoreCase(strategySource.getClass(), getMethodName("findElement"));

            WebElement webElement = (WebElement) methodElement.invoke(selected.getByValue(), driver);

            Method method = ReflectUtil.getMethodByNameIgnoreCase(JavaScriptHandle.class, getMethodName(stepUiNew.getAction()));

            if (StringUtils.isNotBlank(optionsData)) {

                method.invoke(javaScriptHandle,optionsData, webElement, driver);
                strategy.sleep(stepUiNew.getWaite());

            } else if (StringUtils.isBlank(optionsData) && null != webElement) {

                method.invoke(javaScriptHandle,webElement, driver);
                strategy.sleep(stepUiNew.getWaite());

            } else {

                method.invoke(javaScriptHandle,driver);
                strategy.sleep(stepUiNew.getWaite());

            }

        } else if (stepUiNew.getActionType().trim().equals(ActionType.ALTERTYPE.message())) {

            Method method = ReflectUtil.getMethodByNameIgnoreCase(BrowserHandle.class, getMethodName(stepUiNew.getAction()));
            method.invoke(browserHandle,driver);
            strategy.sleep(stepUiNew.getWaite());

        } else if (stepUiNew.getActionType().trim().equals(ActionType.KETBOARD.message())) {

            keyboardHandle.keyboardOperation(driver, stepUiNew.getAction());
            strategy.sleep(stepUiNew.getWaite());

        } else if (stepUiNew.getActionType().trim().equals(ActionType.MOUSE.message())) {

            Method method = ReflectUtil.getMethodByNameIgnoreCase(BrowserHandle.class, getMethodName(stepUiNew.getAction()));
            method.invoke(browserHandle,selected.getByValue(), driver);
            strategy.sleep(stepUiNew.getWaite());

        } else if (stepUiNew.getActionType().trim().equals(ActionType.FILETYPE.message())) {
            SelectorStrategy strategySource = AnnotationSelectorStrategyFactory.getSelectorStrategy(SelectorType.valueOf(selected.getByType()));

            if ("input元素上传".equals(stepUiNew.getAction())) {

                Method inputMethod = ReflectUtil.getMethodByNameIgnoreCase(strategySource.getClass(), getMethodName("findElementClearAndSendKeys"));
                inputMethod.invoke(strategySource.getClass().newInstance(),selected.getByValue(), optionsData, driver);

            } else {

                Method inputMethod = ReflectUtil.getMethodByNameIgnoreCase(strategySource.getClass(), getMethodName("findElementClick"));
                inputMethod.invoke(selected.getByValue(), driver);
                Method method = ReflectUtil.getMethodByNameIgnoreCase(FileHandle.class, getMethodName(stepUiNew.getAction()));
                method.invoke(fileHandle,optionsData, selected.getByValue());
            }
        }
        stepLogMessage.append("测试步骤").append(stepUiNew.getName()).append("执行: 成功");
        return Result.success(stepLogMessage);
    }

    /**
     * 获取用例变量
     *
     * @param stepUiNew 测试步骤
     * @param context   保存有caseId
     * @return 执行数据
     */
    private String getCaseVar(TStepUiNew stepUiNew, Map<String, String> context) {

        String var = stepUiNew.getOptionData();

        String param = changeString(var);

        if (param.equals(var)||null ==param) {
            return var;
        } else {

            LambdaQueryWrapper<CaseParam> queryWrapper = new LambdaQueryWrapper<>();

            queryWrapper.eq(CaseParam::getName, param);

            queryWrapper.eq(CaseParam::getCaseid, context.get("caseId"));

            return caseParamService.getOne(queryWrapper).getValue();
        }

    }

    /**
     *
     * @param stepUiNew 步骤对象
     * @param result 测试结果
     * @param status  执行状态 0成功 1失败 2跳过
     * @param imgUrl minio 地址
     * @return 更新成功
     */
    private boolean saveExcStepLogs(TStepUiNew stepUiNew, Result result, Integer status,String imgUrl) {

        String logData =  result.getMessage() + ":  " +  result.getData();

        StepUiLog stepUiLog = StepUiLog.builder().stepId(Long.valueOf(stepUiNew.getId())).stepName(stepUiNew.getName())
                .caseId(stepUiNew.getTestcaseId()).createBy(SecurityUtils.getCurrentUsername()).logDetail( logData)
                .createTime(new Date()).status(status).endTime(new Date()).imgname(imgUrl).endFlag(Math.toIntExact(stepUiNew.getSort())).build();

        UpdateWrapper<StepUiLog> updateWrapper = new UpdateWrapper<StepUiLog>()
                .eq("case_id",stepUiLog.getCaseId())
                .eq("step_id",stepUiLog.getStepId());

        return  stepUiLogService.saveOrUpdate(stepUiLog,updateWrapper);
    }

    /**
     * @param keyword 根据关键字获取对象方法
     * @return 对象方法名称
     */
    private String getMethodName(String keyword) {

        MethodsNameByKey[] vars = MethodsNameByKey.values();
        if (StringUtils.isBlank(keyword)) {
            return null;
        }
        for (MethodsNameByKey methodsNameByKey : vars) {
            if (keyword.equals(methodsNameByKey.getKeyName())) {
                log.info(methodsNameByKey.getMethodsNameByKey());
                return methodsNameByKey.getMethodsNameByKey();
            }
        }
        return null;
    }

    /**
     *
     * @param var
     * @return
     */
    public  String changeString(String var) {
        String rule = "\\$\\{(.*?)}";
        Pattern regex = Pattern.compile(rule);
        Matcher matcher = regex.matcher(var);
        String targetName = null;

        if (matcher.find()) {
            targetName = matcher.group(1);
        }else {
            targetName = var;
        }
        return targetName;
    }
}
