package com.snail.abell.permission.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 数据字典详情(SysDictDetail)表实体类
 * 
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "")
@SuppressWarnings("serial")
public class SysDictDetail extends Model<SysDictDetail> implements Serializable {
    private static final long serialVersionUID = 286417122226168116L;
            
    @ApiModelProperty("ID")
    private Long detailId;
            
    @ApiModelProperty("字典id")
    private Long dictId;
            
    @ApiModelProperty("字典标签")
    private String label;
            
    @ApiModelProperty("字典值")
    private String value;
            
    @ApiModelProperty("排序")
    private Integer dictSort;
            
    @ApiModelProperty("创建者")
    private String createBy;
            
    @ApiModelProperty("更新者")
    private String updateBy;
            
    @ApiModelProperty("创建日期")
    private Date createTime;
            
    @ApiModelProperty("更新时间")
    private Date updateTime;
}