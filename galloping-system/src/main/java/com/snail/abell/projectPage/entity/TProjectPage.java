package com.snail.abell.projectPage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * t_client(TProjectPage)表实体类
 *
 * @author Abell
 * @since 2022-06-05 19:11:32
 */
@FieldNameConstants
@Data
@ApiModel(description = "项目页面信息")
@SuppressWarnings("serial")
public class TProjectPage extends Model<TProjectPage> implements Serializable {
    private static final long serialVersionUID = -49660032716850494L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("projectId")
    @TableField(select = false,exist = false)
    private Long projectId;

    @ApiModelProperty("页面名称")
    @NotNull
    @NotBlank
    private String pageName;

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
