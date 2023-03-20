package com.snail.abell.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Abell
 * @date 2023/2/28
 */
@Data
@Builder
public class ConditionDto {

    private Integer   stepId;

    private String  caseId;

    private Integer  type;

}
