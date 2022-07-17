package com.snail.abell.permission.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统日志(SysLog)表实体类
 * 
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysLog extends Model<SysLog> implements Serializable {
    private static final long serialVersionUID = -43232998735466987L;
            
    @ApiModelProperty("ID")
    private Long logId;
            
    @ApiModelProperty("$column.comment")
    private String description;
            
    @ApiModelProperty("$column.comment")
    private String logType;
            
    @ApiModelProperty("$column.comment")
    private String method;
            
    @ApiModelProperty("$column.comment")
    private String params;
            
    @ApiModelProperty("$column.comment")
    private String requestIp;
            
    @ApiModelProperty("$column.comment")
    private Long time;
            
    @ApiModelProperty("$column.comment")
    private String username;
            
    @ApiModelProperty("$column.comment")
    private String address;
            
    @ApiModelProperty("$column.comment")
    private String browser;
            
    @ApiModelProperty("$column.comment")
    private String exceptionDetail;
            
    @ApiModelProperty("$column.comment")
    private Date createTime;
}