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
 * @date  2023/2/26
 */
@ApiModel(value="CaseUiCondition")
@Data
@Builder
@TableName(value = "galloping_snail.case__ui_condition")
public class CaseUiCondition implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="id")
    private Integer id;

    @TableField(value = "`action`")
    @ApiModelProperty(value="action")
    private String action;

    /**
     * 0:使用 1：不使用
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="0:使用 1：不使用")
    private boolean status;

    /**
     * 0:前置  1：后置
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="0:前置  1：后置")
    private Integer type;

    @TableField(value = "case_id")
    @ApiModelProperty(value="caseId")
    private String caseId;

    @TableField(value = "step_id")
    @ApiModelProperty(value="stepId")
    private Integer stepId;

    @TableField(value = "`variable`")
    @ApiModelProperty(value="variable")
    private String variable;

    @TableField(value = "`var_name`")
    @ApiModelProperty(value="varName")
    private String varName;

    @TableField(value = "taget_name")
    @ApiModelProperty(value="tagetName")
    private String tagetName;

    @TableField(value = "taget_image")
    @ApiModelProperty(value="tagetImage")
    private String tagetImage;

    @TableField(value = "js_content")
    @ApiModelProperty(value="jsContent")
    private String jsContent;

    @TableField(value = "element_action")
    @ApiModelProperty(value="elementAction")
    private String elementAction;

    @TableField(value = "element_xpath")
    @ApiModelProperty(value="elementAction")
    private String elementXpath;

    private static final long serialVersionUID = 1L;
}
