package com.snail.abell.dto;

import com.snail.abell.entity.TStepUiNew;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Abell
 * @date 2022/11/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StepMessageDto extends TStepUiNew  {

    private PageObject page;

    private ElementObject element;
}
