package com.snail.abell.service.impl;

import com.snail.abell.dao.BaseJob;
import com.snail.abell.entity.TEnv;
import com.snail.abell.entity.TTestcaseUiNew;
import com.snail.abell.entity.TTestsuiteUi;
import com.snail.abell.service.DebugTestCaseService;
import com.snail.abell.service.TEnvService;
import com.snail.abell.service.TTestcaseUiNewService;
import com.snail.abell.service.TTestsuiteUiService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Abell
 * @date 2023/3/14
 */
@Slf4j
@DisallowConcurrentExecution
public class UiExectionJob implements BaseJob {

    @Resource
    private DebugTestCaseService debugTestCaseService;
    @Resource
    private TTestsuiteUiService testsuiteUiService;
    @Resource
    private TTestcaseUiNewService testCaseService;
    @Resource
    private TEnvService envService;


    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();

        //工作任务名称
        String jobName = jobKey.getName();

        ArrayList<Map<String,String>> list = new ArrayList<>();

        Long suitId = testsuiteUiService.lambdaQuery().eq(TTestsuiteUi::getName,jobName).one().getId();

        List<TTestcaseUiNew> listcase = testCaseService.lambdaQuery().eq(TTestcaseUiNew::getSuiteId,suitId).list();

        for (TTestcaseUiNew caseUi : listcase) {

            Map<String,String> context = new HashMap<>();
            String browser = envService.lambdaQuery().eq(TEnv::getId,caseUi.getEnvId()).one().getName();
            context.put("browser",browser);
            context.put("caseId",caseUi.getCaseId());
            list.add(context);
        }

        log.warn(jobName + "Job 执行时间: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        for (Map<String,String> content: list) {

            debugTestCaseService.runTestCase(content);

        }

        log.warn(jobName + "Job 结束时间: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
