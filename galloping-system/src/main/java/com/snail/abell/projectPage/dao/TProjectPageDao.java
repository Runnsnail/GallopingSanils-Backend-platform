package com.snail.abell.projectPage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.projectPage.entity.TProjectPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * t_client(TProjectPage)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-05 19:11:33
 */
public interface TProjectPageDao extends BaseMapper<TProjectPage>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TProjectPage queryById(@Param("id")Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TProjectPage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tProjectPage 实例对象
     * @return 对象列表
     */
    List<TProjectPage> queryAll(TProjectPage tProjectPage);

    /**
     * 新增数据
     *
     * @param tProjectPage 实例对象
     * @return 影响行数
     */
    @Override
    int insert(TProjectPage tProjectPage);

    /**
     * 修改数据
     *
     * @param tProjectPage 实例对象
     * @return 影响行数
     */
    int update(TProjectPage tProjectPage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    void insertSelective(TProjectPage projectPage);

    void updateByPrimaryKey(TProjectPage projectPage);

    void deleteByPrimaryKey(Long id);

    List<TProjectPage> findByProjectIdAndPageName(Long projectId, String pageName);
}