package com.snail.abell.dto;

import lombok.Data;

/**
 * @author Abell
 * @date 2022/11/28
 */
@Data
public class StepInforDto {


    private Integer id;

    private String name;

    private String actionType;

    private String action;

    private String optionData;

    private boolean isEnable;

    private String remark;

    private Long pageId;

    private Long elementId;

    private Long counts;

    private Long waite;
}
