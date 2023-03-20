package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.CaseUiCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Abell
 * @date  2023/2/26
 */
@Mapper
public interface CaseUiConditionMapper extends BaseMapper<CaseUiCondition> {
    int updateBatchSelective(List<CaseUiCondition> list);
}
