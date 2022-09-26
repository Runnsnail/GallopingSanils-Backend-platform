package com.snail.abell.projectPage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.projectPage.entity.Project;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Abell
 * @date  2022/9/19
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
    int updateBatch(List<Project> list);

    int updateBatchSelective(List<Project> list);

    int batchInsert(@Param("list") List<Project> list);

    int insertOrUpdate(Project record);

    int insertOrUpdateSelective(Project record);
}
