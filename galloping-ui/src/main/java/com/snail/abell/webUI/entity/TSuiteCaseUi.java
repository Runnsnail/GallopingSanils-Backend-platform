package com.snail.abell.webUI.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (TSuiteCaseUi)表实体类
 * 
 * @author Abell
 * @since 2022-06-26 12:30:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class TSuiteCaseUi extends Model<TSuiteCaseUi> implements Serializable {
    private static final long serialVersionUID = -52805622993666209L;
            
    @ApiModelProperty("$column.comment")
    private Long id;
            
    @ApiModelProperty("$column.comment")
    private Long suiteId;
            
    @ApiModelProperty("$column.comment")
    private Long caseId;
            
    @ApiModelProperty("$column.comment")
    private Integer sort;
            
    @ApiModelProperty("1:有效，2：无效")
    private Integer isValid;
}