package com.snail.abell.permission.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 岗位(SysJob)表实体类
 * 
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysJob extends Model<SysJob> implements Serializable {
    private static final long serialVersionUID = -61128297309407303L;
            
    @ApiModelProperty("ID")
    private Long jobId;
            
    @ApiModelProperty("岗位名称")
    private String name;
            
    @ApiModelProperty("岗位状态")
    private Object enabled;
            
    @ApiModelProperty("排序")
    private Integer jobSort;
            
    @ApiModelProperty("创建者")
    private String createBy;
            
    @ApiModelProperty("更新者")
    private String updateBy;
            
    @ApiModelProperty("创建日期")
    private Date createTime;
            
    @ApiModelProperty("更新时间")
    private Date updateTime;
}