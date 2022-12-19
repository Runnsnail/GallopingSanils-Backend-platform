package com.snail.abell.permission.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysProject)表实体类
 *
 * @author Abell
 * @since 2022-06-05 16:15:32
 */
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@Data
@ApiModel(description = "项目实体类")
@SuppressWarnings("serial")
public class SysProject extends Model<SysProject> implements Serializable {
    private static final long serialVersionUID = 647964178935707264L;

    @ApiModelProperty("项目ID")
    @TableId(value = "log_id",type = IdType.INPUT)
    private Long id;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("项目编码")
    private String description;

    @ApiModelProperty("1.andorid 2.iOS 3.web 4.other")
    private Integer platform;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("更新人")
    private String updateBy;
}
