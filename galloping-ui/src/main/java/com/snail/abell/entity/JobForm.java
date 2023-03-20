package com.snail.abell.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 定时详情
 * @author Abell
 * @date 2023/3/13
 */
@Data
@Builder
public class JobForm {

    /**
     * 定时任务全类名
     */
    @NotBlank(message = "类名不能为空")
    private String jobClassName;
    /**
     * 任务组名
     */
    @NotBlank(message = "任务组名不能为空")
    private String jobGroupName;
    /**
     * 定时任务cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
    /**
     * 任务名称
     */
    private String jobName;

}
