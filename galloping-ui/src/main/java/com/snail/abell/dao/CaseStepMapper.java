package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.CaseStep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Abell
 * @date  2022/10/29
 */
@Mapper
public interface CaseStepMapper extends BaseMapper<CaseStep> {
    int updateBatch(List<CaseStep> list);

    int updateBatchSelective(List<CaseStep> list);

    int batchInsert(@Param("list") List<CaseStep> list);

    int insertOrUpdate(CaseStep record);

    int insertOrUpdateSelective(CaseStep record);
}
