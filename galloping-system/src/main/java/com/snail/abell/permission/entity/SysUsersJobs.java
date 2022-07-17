package com.snail.abell.permission.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (SysUsersJobs)表实体类
 * 
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysUsersJobs extends Model<SysUsersJobs> implements Serializable {
    private static final long serialVersionUID = 556204215719913312L;
            
    @ApiModelProperty("用户ID")
    private Long userId;
            
    @ApiModelProperty("岗位ID")
    private Long jobId;
}