package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.CaseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Abell
 * @date  2023/2/5
 */
@Mapper
public interface CaseParamMapper extends BaseMapper<CaseParam> {
    int updateBatchSelective(List<CaseParam> list);
}
