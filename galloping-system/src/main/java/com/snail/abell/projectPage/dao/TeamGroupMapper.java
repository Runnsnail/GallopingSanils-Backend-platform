package com.snail.abell.projectPage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.projectPage.entity.TeamGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Abell
 * @date  2022/9/8
 */
public interface TeamGroupMapper extends BaseMapper<TeamGroup> {
    int updateBatch(List<TeamGroup> list);

    int updateBatchSelective(List<TeamGroup> list);

    int batchInsert(@Param("list") List<TeamGroup> list);

    int insertOrUpdate(TeamGroup record);

    int insertOrUpdateSelective(TeamGroup record);
}
