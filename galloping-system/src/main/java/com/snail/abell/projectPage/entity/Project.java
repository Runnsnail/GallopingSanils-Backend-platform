package com.snail.abell.projectPage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Abell
 * @date  2022/9/19
 */
@ApiModel(value="com-snail-abell-projectPage-entity-Project")
@Data
@TableName(value = "galloping_snail.project")
public class Project implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="id")
    private Integer id;

    @TableField(value = "`name`")
    @ApiModelProperty(value="名称")
    private String name;

    @TableField(value = "author")
    @ApiModelProperty(value="作者")
    private String author;

    @TableField(value = "image")
    @ApiModelProperty(value="图片")
    private String image;

    @TableField(value = "rating")
    @ApiModelProperty(value="星星数")
    private Integer rating;

    @TableField(value = "description")
    @ApiModelProperty(value="项目介绍")
    private String description;

    @TableField(value = "card_title")
    @ApiModelProperty(value="项目标签")
    private String cardTitle;

    @TableField(value = "`level`")
    @ApiModelProperty(value="项目级别")
    private String level;

    private static final long serialVersionUID = 1L;
}
