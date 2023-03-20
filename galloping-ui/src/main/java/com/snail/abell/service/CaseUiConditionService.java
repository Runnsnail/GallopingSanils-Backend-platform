package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.dto.ConditionDto;
import com.snail.abell.entity.CaseUiCondition;

import java.util.List;
/**
 * @author Abell
 * @date  2023/2/26
 */
public interface CaseUiConditionService extends IService<CaseUiCondition>{


    int updateBatchSelective(List<CaseUiCondition> list);

    CaseUiCondition fetchConditionByStepId(ConditionDto conditionDto);

    boolean saveOrUpdateByCondition(CaseUiCondition caseUiCondition);
}
