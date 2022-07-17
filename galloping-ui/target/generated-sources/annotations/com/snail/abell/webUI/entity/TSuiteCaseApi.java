package com.snail.abell.webUI.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (TSuiteCaseApi)表实体类
 * 
 * @author Abell
 * @since 2022-06-26 19:19:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class TSuiteCaseApi extends Model<TSuiteCaseApi> implements Serializable {
    private static final long serialVersionUID = 344063752631976402L;
            
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