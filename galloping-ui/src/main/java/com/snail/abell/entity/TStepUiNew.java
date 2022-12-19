package com.snail.abell.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Abell
 * @date  2022/10/29
 */
@ApiModel(value="新测试步骤")
@Data
@TableName(value = "galloping_snail.t_step_ui_new")
public class TStepUiNew implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 描述
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="描述")
    private String name;

    /**
     * 关键字分类
     */
    @TableField(value = "action_type")
    @ApiModelProperty(value="关键字分类")
    private String actionType;

    /**
     * 关键字
     */
    @TableField(value = "`action`")
    @ApiModelProperty(value="关键字")
    private String action;

    @TableField(value = "testcase_id")
    @ApiModelProperty(value="测试用例ID")
    private String testcaseId;

    @TableField(value = "page_id")
    @ApiModelProperty(value="页面ID")
    private Long pageId;

    /**
     * 元素id或数据库id
     */
    @TableField(value = "element_id")
    @ApiModelProperty(value="元素id或数据库id")
    private Long elementId;

    /**
     * 1: UI 2: APP 3:接口
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="1: UI 2: APP 3:接口")
    private Integer type;

    @TableField(value = "create_by")
    @ApiModelProperty(value="")
    private String createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    @TableField(value = "update_by")
    @ApiModelProperty(value="")
    private String updateBy;

    @TableField(value = "update_time")
    @ApiModelProperty(value="")
    private Date updateTime;

    @TableField(value = "sort")
    @ApiModelProperty(value="")
    private Long sort;

    @TableField(value = "icon")
    @ApiModelProperty(value="")
    private String icon;

    @TableField(value = "`variant`")
    @ApiModelProperty(value="")
    private String variant;

    @TableField(value = "is_enable")
    @ApiModelProperty(value="")
    private boolean isEnable;

    /**
     * 关键字描述
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="关键字描述")
    private String remark;

    @TableField(value = "option_data")
    @ApiModelProperty(value="")
    private String optionData;

    @TableField(value = "waite")
    @ApiModelProperty(value="等待时间")
    private Integer waite;

    @TableField(value = "counts")
    @ApiModelProperty(value="点击次数")
    private Integer counts;

    private static final long serialVersionUID = 145355354545L;
}
