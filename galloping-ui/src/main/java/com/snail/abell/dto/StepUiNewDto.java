package com.snail.abell.dto;

import com.snail.abell.entity.TStepUiNew;
import lombok.Data;

/**
 * @author Abell
 * @date 2022/6/20
 */
@Data
public class StepUiNewDto extends TStepUiNew {
    private String byType;
    private String byValue;
}
