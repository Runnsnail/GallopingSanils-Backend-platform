package com.snail.abell.permission.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户角色关联(SysUsersRoles)表实体类
 * 
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysUsersRoles extends Model<SysUsersRoles> implements Serializable {
    private static final long serialVersionUID = 794862721558972782L;
            
    @ApiModelProperty("用户ID")
    private Long userId;
            
    @ApiModelProperty("角色ID")
    private Long roleId;


}