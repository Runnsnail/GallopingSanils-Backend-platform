package com.snail.abell.dto;

import com.snail.abell.entity.TStepUiNew;
import com.snail.abell.entity.TTestcaseUiNew;
import lombok.Data;

import java.util.List;

/**
 * @author Abell
 */
@Data
public class TestUiDto extends TTestcaseUiNew {

    private static final long serialVersionUID = 1L;
    private Long suiteId;
    private Long suiteCaseId;
    private List<TStepUiNew> testSteps;
    /**
     * 排序
     */
    private Integer sort;
}
