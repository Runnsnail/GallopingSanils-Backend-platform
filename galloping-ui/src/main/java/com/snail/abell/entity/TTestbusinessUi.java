package com.snail.abell.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (TTestbusinessUi)表实体类
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class TTestbusinessUi extends Model<TTestbusinessUi> implements Serializable {
    private static final long serialVersionUID = 405623097491968809L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("$column.comment")
    private String name;

    @ApiModelProperty("$column.comment")
    private Long projectId;

    @ApiModelProperty("执行完毕后是否关闭浏览器:1、关闭 0、不关闭")
    private Long closeBrowser;

    @ApiModelProperty("超时时间，单位秒")
    private Long timoutTime;

    @ApiModelProperty("失败了是否继续0：不继续，1：继续")
    private Integer failContinue;

    @ApiModelProperty("$column.comment")
    private String remark;

    @ApiModelProperty("$column.comment")
    private String createBy;

    @ApiModelProperty("$column.comment")
    private String updateBy;

    @ApiModelProperty("$column.comment")
    private Date createTime;

    @ApiModelProperty("$column.comment")
    private Date updateTime;
}
