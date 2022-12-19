package com.snail.abell.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(SysRole)表实体类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "角色实体类")
@SuppressWarnings("serial")
public class SysRole extends Model<SysRole> implements Serializable {
    private static final long serialVersionUID = 687239134406025649L;

    @ApiModelProperty("ID")
    @TableId(value = "role_id",type = IdType.INPUT)
    private Long roleId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("角色级别")
    private Integer level;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("数据权限")
    private String dataScope;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("创建日期")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
