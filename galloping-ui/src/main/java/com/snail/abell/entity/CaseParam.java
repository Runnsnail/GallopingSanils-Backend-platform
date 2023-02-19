package com.snail.abell.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Abell
 * @date  2023/2/5
 */
@ApiModel(value="com-snail-abell-projectPage-entity-CaseParam")
@Data
@TableName(value = "galloping_snail.case_param")
public class CaseParam implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 用例ID
     */
    @TableField(value = "caseId")
    @ApiModelProperty(value="用例ID")
    private String caseid;

    /**
     * 参数名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="参数名称")
    private String name;

    /**
     * 参数值
     */
    @TableField(value = "`value`")
    @ApiModelProperty(value="参数值")
    private String value;

    /**
     * 参数值
     */
    @TableField(value = "`describe`")
    @ApiModelProperty(value="参数值")
    private String describe;

    private static final long serialVersionUID = 1L;
}
