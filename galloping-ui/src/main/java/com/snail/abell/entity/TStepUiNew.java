package com.snail.abell.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (TStepUiNew)表实体类
 *
 * @author Abell
 * @since 2022-06-26 12:30:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class TStepUiNew extends Model<TStepUiNew> implements Serializable {
    private static final long serialVersionUID = 478387983503323072L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("描述")
    private String name;

    @ApiModelProperty("关键字分类")
    private String actionType;

    @ApiModelProperty("关键字")
    private String action;

    @ApiModelProperty("关键字描述")
    private String actionRemark;

    @ApiModelProperty("$column.comment")
    private Long testcaseId;

    @ApiModelProperty("$column.comment")
    private Long pageId;

    @ApiModelProperty("元素id或数据库id")
    private Long elementId;

    @ApiModelProperty("$column.comment")
    private String optionData;

    @ApiModelProperty("1: UI 2: APP 3:接口")
    private Integer type;

    @ApiModelProperty("$column.comment")
    private String createBy;

    @ApiModelProperty("$column.comment")
    private Date createTime;

    @ApiModelProperty("$column.comment")
    private String updateBy;

    @ApiModelProperty("$column.comment")
    private Date updateTime;

    @ApiModelProperty("$column.comment")
    private Long sort;
}
