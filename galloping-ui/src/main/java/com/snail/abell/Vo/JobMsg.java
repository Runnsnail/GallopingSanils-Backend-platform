package com.snail.abell.Vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Abell
 * @date 2023/3/14
 */
@Data
public class JobMsg {

    /**
     * 定时任务cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空")
    private String jobName;
}
