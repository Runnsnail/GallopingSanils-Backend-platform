package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dao.CaseStepMapper;
import com.snail.abell.entity.CaseStep;
import com.snail.abell.service.CaseStepService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Abell
 * @date  2022/10/29
 */
@Service
public class CaseStepServiceImpl extends ServiceImpl<CaseStepMapper, CaseStep> implements CaseStepService {

    @Override
    public int updateBatch(List<CaseStep> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<CaseStep> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<CaseStep> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(CaseStep record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(CaseStep record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
