package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.snail.abell.config.ActionType;
import com.snail.abell.config.DriverFactory;
import com.snail.abell.dao.TStepUiNewMapper;
import com.snail.abell.entity.CaseParam;
import com.snail.abell.entity.CaseStep;
import com.snail.abell.entity.TStepUiNew;
import com.snail.abell.entity.TTestcaseUiNew;
import com.snail.abell.exception.BizException;
import com.snail.abell.service.*;
import com.snail.abell.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.snail.abell.base.ResultCode.DRIVER_EXIST_ERROR;
import static com.snail.abell.base.ResultCode.SCEN_EXIST_ERROR;

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
    private TStepUiNewService stepUiNewService;
    @Resource
    private CaseStepService caseStepService;
    @Resource
    private CaseParamService caseParamService;

    public Logger log = LogManager.getLogger("TestCaseStep");

    @Override
    public boolean runTestCase(Map<String, String> context) {

        String browserType = context.get("browser");
        String caseId = context.get("caseId");
        DriverFactory.initDriver(browserType);
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            throw new BizException(DRIVER_EXIST_ERROR);
        }
        TTestcaseUiNew testcaseUiNew = testcaseUiNewService.getById(caseId);
        List<CaseStep> caseStepList = caseStepService.lambdaQuery().eq(CaseStep::getCaseId, caseId).list();
        List<Integer> stepIds = caseStepList.stream().map(CaseStep::getStepId).collect(Collectors.toList());
        List<TStepUiNew> stepList = stepUiNewMapper.selectBatchIds(stepIds);
        List<TStepUiNew> sortsList = stepList.stream().sorted(Comparator.comparing(TStepUiNew::getSort)).collect(Collectors.toList());
        //获取用例内部参数
        log.info("执行测试用例:[{}]开始。。。", testcaseUiNew.getName());
        log.info("获取测试步骤");
        log.info("打开浏览器：{}，设置隐式等待{}秒", browserType, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        for (TStepUiNew stepUiNew: sortsList) {
          boolean flag = isExcBusiness(stepUiNew,context);
        }
        return true;
    }

    @Override
    public boolean isExcBusiness(TStepUiNew stepUiNew, Map<String, String> context) {

        String value = this.getCaseVar(stepUiNew,context);
        saveExcStepLogs(stepUiNew);

        if (stepUiNew.getActionType().trim().equals(ActionType.SCENTYPE.message()))
        {
            context.put("caseId",stepUiNew.getOptionData());
            boolean excScen =  runTestCase(context);
            if(!excScen){throw new BizException(SCEN_EXIST_ERROR);}
        }else if(stepUiNew.getActionType().trim().equals(ActionType.BROWER.message())){

        }else if(stepUiNew.getActionType().trim().equals(ActionType.COOKERTYPE.message())){

        }else if(stepUiNew.getActionType().trim().equals(ActionType.ELEMENTTYPE.message())){

        }else if(stepUiNew.getActionType().trim().equals(ActionType.JS.message())){

        }else if(stepUiNew.getActionType().trim().equals(ActionType.ALTERTYPE.message())){

        }else if(stepUiNew.getActionType().trim().equals(ActionType.KETBOARD.message())){

        }else if(stepUiNew.getActionType().trim().equals(ActionType.MOUSE.message())){

        }else if(stepUiNew.getActionType().trim().equals(ActionType.FILETYPE.message())){

        }

        return false;
    }

    /**
     * 获取用例变量
     * @param stepUiNew 测试步骤
     * @param context 保存有caseId
     * @return  执行数据
     */
    private  String getCaseVar(TStepUiNew stepUiNew,Map<String, String> context){
        String var = stepUiNew.getOptionData();
        String param = StringUtils.changeString(var);
        LambdaQueryWrapper<CaseParam> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CaseParam::getName,param);
        queryWrapper.eq(CaseParam::getCaseid,context.get("caseId"));
        return caseParamService.getOne(queryWrapper).getValue();
    }

    private void saveExcStepLogs(TStepUiNew stepUiNew){

    }
}
