package com.snail.abell.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志(SysQuartzLog)表实体类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysQuartzLog extends Model<SysQuartzLog> implements Serializable {
    private static final long serialVersionUID = -44100570920540603L;

    @ApiModelProperty("ID")
    @TableId(value = "log_id",type = IdType.INPUT)
    private Long logId;

    @ApiModelProperty("$column.comment")
    private String beanName;

    @ApiModelProperty("$column.comment")
    private Date createTime;

    @ApiModelProperty("$column.comment")
    private String cronExpression;

    @ApiModelProperty("$column.comment")
    private String exceptionDetail;

    @ApiModelProperty("$column.comment")
    private Object isSuccess;

    @ApiModelProperty("$column.comment")
    private String jobName;

    @ApiModelProperty("$column.comment")
    private String methodName;

    @ApiModelProperty("$column.comment")
    private String params;

    @ApiModelProperty("$column.comment")
    private Long time;
}
