package com.snail.abell.controller;

import com.snail.abell.Vo.JobMsg;
import com.snail.abell.base.Result;
import com.snail.abell.entity.JobForm;
import com.snail.abell.service.JobService;
import com.snail.abell.utils.JobUtil;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;

/**
 * @author Abell
 * @date 2023/3/13
 */

@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private  JobService jobService;


    /**
     * 保存定时任务
     */
    @PostMapping("/add")
    public Result addJob(@Valid JobMsg jobMsg) throws ParseException {

        JobForm form = JobUtil.changeJobForm(jobMsg);

        try {
            jobService.addJob(form);
        } catch (Exception e) {
            return Result.success("添加定时任务失败");
        }

        return Result.success();
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/delete")
    public Result deleteJob(JobMsg jobMsg) throws ParseException {

        JobForm form = JobUtil.changeJobForm(jobMsg);

        try {
            jobService.deleteJob(form);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.success("删除失败");
        }
        return Result.success();
    }

    /**
     * 暂停定时任务
     */
    @PutMapping("/pause")
    public Result pauseJob(JobMsg jobMsg) throws ParseException {

        JobForm form = JobUtil.changeJobForm(jobMsg);

        try {
            jobService.pauseJob(form);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.success("暂停失败");
        }
        return Result.success();
    }

    /**
     * 恢复定时任务
     */
    @PutMapping("/resume")
    public Result resumeJob(JobMsg jobMsg) throws ParseException {

        JobForm form = JobUtil.changeJobForm(jobMsg);

        try {
            jobService.resumeJob(form);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.success("恢复失败");
        }
        return Result.success();
    }

    /**
     * 修改定时任务，定时时间
     * @param jobMsg
     * @return
     */
    @PutMapping("/cron")
    public Result cronJob(@Valid JobMsg jobMsg) throws ParseException {

        JobForm form = JobUtil.changeJobForm(jobMsg);
        try {
            jobService.cronJob(form);
        } catch (Exception e) {
            return Result.success("修改失败");
        }

        return Result.success();
    }

}
