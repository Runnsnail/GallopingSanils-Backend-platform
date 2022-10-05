package com.snail.abell.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (TTestsuiteUi)表实体类
 *
 * @author Abell
 * @since 2022-06-28 15:34:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "测试用例集")
@SuppressWarnings("serial")
public class TTestsuiteUi extends Model<TTestsuiteUi> implements Serializable {
    private static final long serialVersionUID = -59585539252183271L;

    @ApiModelProperty("用例集ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("项目名称")
    private Long projectId;

    @ApiModelProperty("父级ID")
    private Long parentId;

    @ApiModelProperty("排序")
    private String sort;

    @ApiModelProperty("0：代表文件夹，1：代表测试用例")
    private Integer type;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("用例子集")
    private List<TTestsuiteUi> children;


}
