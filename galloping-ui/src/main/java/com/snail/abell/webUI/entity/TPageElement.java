package com.snail.abell.webUI.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_client(TPageElement)表实体类
 * 
 * @author Abell
 * @since 2022-06-05 21:25:36
 */

@ApiModel(description = "页面元素信息")
@Data
@SuppressWarnings("serial")
public class TPageElement extends Model<TPageElement> implements Serializable {
    private static final long serialVersionUID = 972726686804335266L;
            
    @ApiModelProperty("id")
    private Long id;
            
    @ApiModelProperty("页面id")
    private Long pageId;


    @ApiModelProperty("元素名称")
    private String elementName;
            
    @ApiModelProperty("定位方式")
    private String byType;
            
    @ApiModelProperty("定位值")
    private String byValue;
            
    @ApiModelProperty("1：有效")
    private Integer isEnable;
            
    @ApiModelProperty("备注")
    private String remark;
            
    @ApiModelProperty("createBy")
    private String createBy;
            
    @ApiModelProperty("createTime")
    private Date createTime;
            
    @ApiModelProperty("updateBy")
    private String updateBy;
            
    @ApiModelProperty("updateTime")
    private Date updateTime;
}