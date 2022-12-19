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
 * 系统用户(SysUser)表实体类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "用户")
@SuppressWarnings("serial")
public class SysUser extends Model<SysUser> implements Serializable {
    private static final long serialVersionUID = -95057445747590609L;

    @ApiModelProperty("ID")
    @TableId(value = "user_id",type = IdType.INPUT)
    private Long userId;

    @ApiModelProperty("部门名称")
    private Long deptId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("团队code")
    private String member;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像地址")
    private String avatarName;

    @ApiModelProperty("头像真实路径")
    private String avatarPath;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("是否为admin账号")
    private Object isAdmin;

    @ApiModelProperty("状态：1启用、0禁用")
    private Long enabled;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("修改密码的时间")
    private Date pwdResetTime;

    @ApiModelProperty("创建日期")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
