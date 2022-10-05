package com.snail.abell.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (TPlanBusinessUi)表实体类
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class TPlanBusinessUi extends Model<TPlanBusinessUi> implements Serializable {
    private static final long serialVersionUID = -76587340844890346L;

    @ApiModelProperty("$column.comment")
    private Long id;

    @ApiModelProperty("$column.comment")
    private Long jobId;

    @ApiModelProperty("$column.comment")
    private Long businessId;

    @ApiModelProperty("$column.comment")
    private Integer sort;

    @ApiModelProperty("1:有效，2：无效")
    private Integer isValid;
}
