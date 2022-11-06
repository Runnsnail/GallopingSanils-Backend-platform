package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.TEnv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Abell
 * @date  2022/11/4
 */
@Mapper
public interface TEnvMapper extends BaseMapper<TEnv> {
    int updateBatch(List<TEnv> list);

    int updateBatchSelective(List<TEnv> list);

    int batchInsert(@Param("list") List<TEnv> list);

    int insertOrUpdate(TEnv record);

    int insertOrUpdateSelective(TEnv record);
}
