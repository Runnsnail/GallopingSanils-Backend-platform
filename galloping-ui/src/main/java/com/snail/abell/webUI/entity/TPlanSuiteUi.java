package com.snail.abell.webUI.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (TPlanSuiteUi)表实体类
 * 
 * @author Abell
 * @since 2022-06-26 16:36:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class TPlanSuiteUi extends Model<TPlanSuiteUi> implements Serializable {
    private static final long serialVersionUID = -58072696924941443L;
            
    @ApiModelProperty("$column.comment")
    private Long id;
            
    @ApiModelProperty("$column.comment")
    private Long jobId;
            
    @ApiModelProperty("$column.comment")
    private Long suiteId;
            
    @ApiModelProperty("$column.comment")
    private Integer sort;
            
    @ApiModelProperty("1:有效，2：无效")
    private Integer isValid;
}