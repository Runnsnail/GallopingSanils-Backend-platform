package com.snail.abell.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色菜单关联(SysRolesMenus)表实体类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysRolesMenus extends Model<SysRolesMenus> implements Serializable {
    private static final long serialVersionUID = 832525766325860143L;

    @ApiModelProperty("菜单ID")
    @TableId(value = "menu_id",type = IdType.INPUT)
    private Long menuId;

    @ApiModelProperty("角色ID")
    private Long roleId;
}
