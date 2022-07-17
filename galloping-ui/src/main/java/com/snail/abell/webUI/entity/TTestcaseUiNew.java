package com.snail.abell.webUI.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (TTestcaseUiNew)表实体类
 * 
 * @author Abell
 * @since 2022-06-26 11:09:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class TTestcaseUiNew extends Model<TTestcaseUiNew> implements Serializable {
    private static final long serialVersionUID = -97200253539647309L;
            
    @ApiModelProperty("$column.comment")
    private Long id;
            
    @ApiModelProperty("$column.comment")
    private String name;
            
    @ApiModelProperty("环境id")
    private Long envId;
            
    @ApiModelProperty("$column.comment")
    private Long projectId;
            
    @ApiModelProperty("超时时间，单位分")
    private Long timoutTime;
            
    @ApiModelProperty("失败了是否继续0：不继续，1：继续")
    private Integer failContinue;
            
    @ApiModelProperty("标签")
    private String flags;
            
    @ApiModelProperty("业务名参数")
    private String params;
            
    @ApiModelProperty("用例内部参数")
    private String caseVars;
            
    @ApiModelProperty("1,用例、2：业务")
    private Long caseType;
            
    @ApiModelProperty("备注")
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