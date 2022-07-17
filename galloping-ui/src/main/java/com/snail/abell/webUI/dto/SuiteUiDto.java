package com.snail.abell.webUI.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Abell
 * @date
 */
@Data
public class SuiteUiDto implements Serializable {

    private Long id;

    private String name;

    private Long projectId;

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}
