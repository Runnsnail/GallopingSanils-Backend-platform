package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.entity.JobAndTrigger;
import com.snail.abell.entity.JobForm;
import org.quartz.SchedulerException;

/**
 * @author Abell
 * @date 2023/3/13
 */
public interface JobService  extends IService<JobAndTrigger> {

    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    void addJob(JobForm form) throws Exception;

    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobForm form) throws SchedulerException;

    /**
     * 暂停定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobForm form) throws SchedulerException;

    /**
     * 恢复定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void resumeJob(JobForm form) throws SchedulerException;

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    void cronJob(JobForm form) throws Exception;

    /**
     * 立即执行任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception
     */
    void  runJob(JobForm form) throws Exception;


    /**
     *  获取任务状态
     * (" BLOCKED ", " 阻塞 ");
     * ("COMPLETE", "完成");
     * ("ERROR", "出错");
     * ("NONE", "不存在");
     * ("NORMAL", "正常");
     * ("PAUSED", "暂停");
     * @param form 表单参数 {@link JobForm}
     * @return String 状态
     * @throws Exception
     */
    String getScheduleJobStatus(JobForm form) throws Exception;


}
