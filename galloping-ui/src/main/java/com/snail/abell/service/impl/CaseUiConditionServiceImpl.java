package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dao.CaseUiConditionMapper;
import com.snail.abell.dto.ConditionDto;
import com.snail.abell.entity.CaseUiCondition;
import com.snail.abell.service.CaseUiConditionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Abell
 * @date  2023/2/26
 */
@Service
public class CaseUiConditionServiceImpl extends ServiceImpl<CaseUiConditionMapper, CaseUiCondition> implements CaseUiConditionService {

    @Resource
    private CaseUiConditionMapper caseUiConditionMapper;
    @Resource
    private CaseUiConditionService caseUiConditionService;

    @Override
    public int updateBatchSelective(List<CaseUiCondition> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public CaseUiCondition fetchConditionByStepId(ConditionDto conditionDto) {

        LambdaQueryWrapper<CaseUiCondition> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CaseUiCondition ::getCaseId,conditionDto.getCaseId())
                    .eq(CaseUiCondition ::getStepId,conditionDto.getStepId())
                    .eq(CaseUiCondition ::getType,conditionDto.getType());
        CaseUiCondition  caseUiCondition = caseUiConditionMapper.selectOne(queryWrapper);

        return caseUiCondition;
    }

    @Override
    public boolean saveOrUpdateByCondition(CaseUiCondition caseUiCondition) {

        UpdateWrapper<CaseUiCondition> updateWrapper = new UpdateWrapper<CaseUiCondition>();
        updateWrapper.eq("case_id",caseUiCondition.getCaseId())
                .eq("step_id",caseUiCondition.getStepId());

        return caseUiConditionService.saveOrUpdate(caseUiCondition,updateWrapper);
    }
}
