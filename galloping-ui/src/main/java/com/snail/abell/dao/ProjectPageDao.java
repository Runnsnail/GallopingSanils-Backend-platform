package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.ProjectPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * t_client(TProjectPage)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-05 19:11:33
 */
public interface ProjectPageDao extends BaseMapper<ProjectPage>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectPage queryById(@Param("id")Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProjectPage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectPage 实例对象
     * @return 对象列表
     */
    List<ProjectPage> queryAll(ProjectPage projectPage);

    /**
     * 新增数据
     *
     * @param projectPage 实例对象
     * @return 影响行数
     */
    @Override
    int insert(ProjectPage projectPage);

    /**
     * 修改数据
     *
     * @param projectPage 实例对象
     * @return 影响行数
     */
    int update(ProjectPage projectPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
