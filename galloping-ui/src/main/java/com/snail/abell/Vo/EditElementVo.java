package com.snail.abell.Vo;

import com.snail.abell.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Abell
 * @date 2022/10/6
 */
@Data
public class EditElementVo extends BaseDTO implements Serializable {

    @ApiModelProperty("id")
    private Integer id;

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

    @ApiModelProperty("updateBy")
    private String updateBy;

    @ApiModelProperty("updateTime")
    private Date updateTime;
}
