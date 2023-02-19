package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dao.CaseParamMapper;
import com.snail.abell.entity.CaseParam;
import com.snail.abell.service.CaseParamService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Abell
 * @date  2023/2/5
 */
@Service
public class CaseParamServiceImpl extends ServiceImpl<CaseParamMapper, CaseParam> implements CaseParamService{

    @Override
    public int updateBatchSelective(List<CaseParam> list) {
        return baseMapper.updateBatchSelective(list);
    }
}
