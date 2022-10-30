package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.TStepUiNew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Abell
 * @date  2022/10/29
 */
@Mapper
public interface TStepUiNewMapper extends BaseMapper<TStepUiNew> {
    int updateBatch(List<TStepUiNew> list);

    int updateBatchSelective(List<TStepUiNew> list);

    int batchInsert(@Param("list") List<TStepUiNew> list);

    int insertOrUpdates(TStepUiNew record);

    int insertOrUpdateSelective(TStepUiNew record);

    int insertOrUpdateWithBLOBs(TStepUiNew record);

    List<TStepUiNew> findByTestcaseId(@Param("testcaseId") String testcaseId);
}
