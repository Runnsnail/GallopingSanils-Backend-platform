package com.snail.abell.permission.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 定时任务(SysQuartzJob)表实体类
 * 
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysQuartzJob extends Model<SysQuartzJob> implements Serializable {
    private static final long serialVersionUID = 518699194947583709L;
            
    @ApiModelProperty("ID")
    private Long jobId;
            
    @ApiModelProperty("Spring Bean名称")
    private String beanName;
            
    @ApiModelProperty("cron 表达式")
    private String cronExpression;
            
    @ApiModelProperty("状态：1暂停、0启用")
    private Object isPause;
            
    @ApiModelProperty("任务名称")
    private String jobName;
            
    @ApiModelProperty("方法名称")
    private String methodName;
            
    @ApiModelProperty("参数")
    private String params;
            
    @ApiModelProperty("备注")
    private String description;
            
    @ApiModelProperty("负责人")
    private String personInCharge;
            
    @ApiModelProperty("报警邮箱")
    private String email;
            
    @ApiModelProperty("子任务ID")
    private String subTask;
            
    @ApiModelProperty("任务失败后是否暂停")
    private Object pauseAfterFailure;
            
    @ApiModelProperty("创建者")
    private String createBy;
            
    @ApiModelProperty("更新者")
    private String updateBy;
            
    @ApiModelProperty("创建日期")
    private Date createTime;
            
    @ApiModelProperty("更新时间")
    private Date updateTime;
}