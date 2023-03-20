package com.snail.abell.utils;

import cn.hutool.core.util.IdUtil;
import com.snail.abell.Vo.JobMsg;
import com.snail.abell.dao.BaseJob;
import com.snail.abell.entity.JobForm;

import java.text.ParseException;

/**
 * @author Abell
 * @date 2023/3/13
 */
public class JobUtil {

    /**
     * 根据全类名获取Job实例
     *
     * @param classname Job全类名
     * @return {@link BaseJob} 实例
     * @throws Exception 泛型获取异常
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.newInstance();
    }

    /**
     * Job转换器，设置cron表达式，以及Job类
     * @param jobMsg
     * @return
     * @throws ParseException
     */
    public static JobForm changeJobForm (JobMsg jobMsg) throws ParseException {
        String jobGroupName = jobMsg.getJobName() + IdUtil.simpleUUID();
        String cronExpression = DateUtils.parseCron(jobMsg.getCronExpression());
        JobForm form = JobForm.builder().jobClassName("com.snail.abell.service.impl.UiExectionJob")
                .jobName(jobMsg.getJobName()).cronExpression(cronExpression).jobGroupName(jobGroupName).build();
        return form;
    }



}
