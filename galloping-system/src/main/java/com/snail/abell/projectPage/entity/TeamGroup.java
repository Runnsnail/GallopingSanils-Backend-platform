package com.snail.abell.projectPage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Abell
 * @date  2022/9/8
 */
@ApiModel(value="com-snail-abell-projectPage-entity-TeamGroup")
@Data
@TableName(value = "galloping_snail.team_group")
public class TeamGroup implements Serializable {
    /**
     * 团队id
     */
    @TableId(value = "team_id", type = IdType.INPUT)
    @ApiModelProperty(value="团队id")
    private Integer teamId;

    /**
     * 团队名称
     */
    @TableField(value = "team_name")
    @ApiModelProperty(value="团队名称")
    private String teamName;

    /**
     * 卡片名称
     */
    @TableField(value = "card_title")
    @ApiModelProperty(value="卡片名称")
    private String cardTitle;

    /**
     * 团队描述
     */
    @TableField(value = "team_description")
    @ApiModelProperty(value="团队描述")
    private String teamDescription;

    /**
     * 团队负责渠道
     */
    @TableField(value = "team_responsibility")
    @ApiModelProperty(value="团队负责渠道")
    private String teamResponsibility;

    /**
     * 成员名称
     */
    @TableField(value = "`member`")
    @ApiModelProperty(value="成员名称")
    private String teamMember;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 团队邮箱
     */
    @TableField(value = "team_mail")
    @ApiModelProperty(value="团队邮箱")
    private String teamMail;

    private static final long serialVersionUID = 1L;


}
