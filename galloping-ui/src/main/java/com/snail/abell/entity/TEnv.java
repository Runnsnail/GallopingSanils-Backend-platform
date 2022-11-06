package com.snail.abell.entity;

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
 * @date  2022/11/4
 */
/**
    * 分类表
    */
@ApiModel(value="com-snail-abell-projectPage-entity-TEnv")
@Data
@TableName(value = "galloping_snail.t_env")
public class TEnv implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 环境名字
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="环境名字")
    private String name;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    /**
     * 所属项目的id
     */
    @TableField(value = "project_id")
    @ApiModelProperty(value="所属项目的id")
    private Long projectId;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
