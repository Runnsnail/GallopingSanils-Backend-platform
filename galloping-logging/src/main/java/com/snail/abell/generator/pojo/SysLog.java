package com.snail.abell.generator.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName log
 */
@Data
@TableName(value = "log")
@ApiModel(description = "日志記錄")
public class SysLog implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 异常详细
     */
    @ApiModelProperty("异常详细")
    private String exceptionDetail;

    /**
     * 日志类型
     */
    @ApiModelProperty("日志类型")
    private String logType;

    /**
     * 方法名
     */
    @ApiModelProperty("方法名")
    private String method;

    /**
     * 参数
     */
    @ApiModelProperty("参数")
    private String params;

    /**
     * 请求ip
     */
    @ApiModelProperty("请求ip")
    private String requestIp;

    /**
     * 请求耗时
     */
    @ApiModelProperty("请求耗时")
    private Long time;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 浏览器
     */
    @ApiModelProperty("浏览器")
    private String browser;

    /**
     * 
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Integer isDel;

    /**
     * 
     */
    @ApiModelProperty("创建时间")
    private Date createdate;

    /**
     * 
     */
    @ApiModelProperty("创建人")
    private String createuser;

    /**
     * 
     */
    @ApiModelProperty("修改时间")
    private Date changedate;

    /**
     * 
     */
    @ApiModelProperty("修改人")
    private String changeuser;

    private static final long serialVersionUID = 1L;

    public SysLog(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }


}