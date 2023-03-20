package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.config.JobFireListener;
import com.snail.abell.dao.JobMapper;
import com.snail.abell.entity.JobAndTrigger;
import com.snail.abell.entity.JobForm;
import com.snail.abell.service.JobAndTriggerService;
import com.snail.abell.service.JobService;
import com.snail.abell.utils.JobUtil;
import org.quartz.*;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Abell
 * @date 2023/3/13
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, JobAndTrigger> implements JobService {

    @Autowired
    private  Scheduler scheduler;

    @Autowired
    public JobServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Resource
    private JobAndTriggerService jobAndTriggerService;

    @Override
    public void addJob(JobForm form) throws Exception {
        // 启动调度器
        scheduler.start();

        //作业名称及其组名
        JobKey jobKey = JobKey.jobKey(form.getJobName(), form.getJobGroupName());

        //判断是否有相同的作业
        try {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if(jobDetail != null){
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(form.getJobClassName()).getClass()).withIdentity(form.getJobName(), form.getJobGroupName()).build();

        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());

        //根据Cron表达式构建一个Trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobName(), form.getJobGroupName()).withSchedule(cron).build();

        JobAndTrigger jobAndTrig = JobAndTrigger.builder().jobName(form.getJobName()).jobGroup(form.getJobGroupName())
                .cronExpression(form.getCronExpression()).triggerName(form.getJobName()).timeZoneId(trigger.getTimeZone().toString())
                .build();
        try {
            jobAndTriggerService.insertOrUpdate(jobAndTrig);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】创建失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
    }

    @Override
    public void deleteJob(JobForm form) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobName(), form.getJobGroupName()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobName(), form.getJobGroupName()));
        scheduler.deleteJob(JobKey.jobKey(form.getJobName(), form.getJobGroupName()));
    }

    @Override
    public void pauseJob(JobForm form) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(form.getJobName(), form.getJobGroupName()));
    }

    @Override
    public void resumeJob(JobForm form) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(form.getJobName(), form.getJobGroupName()));
    }

    @Override
    public void cronJob(JobForm form) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobName(), form.getJobGroupName());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 根据Cron表达式构建一个Trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            JobFireListener listener = new JobFireListener();
            ListenerManager mgr = scheduler.getListenerManager();
            //匹配所有job
            mgr.addJobListener(listener, EverythingMatcher.allJobs());

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("【定时任务】更新失败！", e);
            throw new Exception("【定时任务】创建失败！");
        }
    }

    @Override
    public void runJob(JobForm form) throws Exception {

        JobKey jobKey = JobKey.jobKey(form.getJobName(),form.getJobGroupName());

        JobFireListener listener = new JobFireListener();
        ListenerManager mgr = scheduler.getListenerManager();
        //匹配所有job
        mgr.addJobListener(listener, EverythingMatcher.allJobs());
        scheduler.triggerJob(jobKey);

    }


    @Override
    public String getScheduleJobStatus(JobForm form) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobName(), form.getJobGroupName());
        Trigger.TriggerState state = scheduler.getTriggerState(triggerKey);
        return state.name();
    }


}
