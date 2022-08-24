package com.snail.abell.permission.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单(SysMenu)表实体类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "菜单实体类")
@Data
@SuppressWarnings("serial")
public class SysMenu extends Model<SysMenu> implements Serializable {
    private static final long serialVersionUID = 223473916371748259L;

    @TableId(value = "menu_id")
    @ApiModelProperty("ID")
    private Long menuId;

    @ApiModelProperty("上级菜单ID")
    private Long pid;

    @ApiModelProperty("子菜单数目")
    private Integer subCount;

    @ApiModelProperty("菜单类型:0目录 1菜单 2按钮")
    private Integer type;

    @ApiModelProperty("菜单标题")
    @TableField(value = "page_Title")
    private String pageTitle;

    @ApiModelProperty("组件名称")
    private String name;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("排序")
    private Integer menuSort;

    @ApiModelProperty("面包屑一级")
    @TableField(value = "text_one")
    private String textone;

    @ApiModelProperty("面包屑二级级")
    @TableField(value = "text_two")
    private String texttwo;

    @ApiModelProperty("面包屑二级级")
    private Boolean active;

    @ApiModelProperty("链接地址")
    private String path;

    @ApiModelProperty("是否外链")
    @TableField(value = "i_frame")
    private Boolean iframe;

    @ApiModelProperty("隐藏")
    private Boolean hidden;

    @ApiModelProperty("权限")
    private String permission;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("创建日期")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
