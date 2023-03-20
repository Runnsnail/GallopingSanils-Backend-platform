package com.snail.abell.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Abell
 * @date  2023/3/18
 */
@ApiModel(value="com-snail-abell-projectPage-entity-JobAndTrigger")
@Data
@Builder
@TableName(value = "galloping_snail.job_and_trigger")
public class JobAndTrigger implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "job_name")
    @ApiModelProperty(value="定时任务名称")
    private String jobName;

    @TableField(value = "job_group")
    @ApiModelProperty(value="定时任务组")
    private String jobGroup;

    @TableField(value = "job_class_name")
    @ApiModelProperty(value="定时任务全类名")
    private String jobClassName;

    @TableField(value = "`trigger_name`")
    @ApiModelProperty(value="触发器名称")
    private String triggerName;

    @TableField(value = "trigger_group")
    @ApiModelProperty(value="触发器组")
    private String triggerGroup;

    @TableField(value = "repeat_interval")
    @ApiModelProperty(value="重复间隔")
    private Integer repeatInterval;

    @TableField(value = "times_triggered")
    @ApiModelProperty(value="触发次数")
    private Integer timesTriggered;

    @TableField(value = "cron_expression")
    @ApiModelProperty(value="cron 表达式")
    private String cronExpression;

    @TableField(value = "time_zone_id")
    @ApiModelProperty(value="时区")
    private String timeZoneId;

    @TableField(value = "trigger_state")
    @ApiModelProperty(value="定时任务状态")
    private String triggerState;

    private static final long serialVersionUID = 1L;
}
