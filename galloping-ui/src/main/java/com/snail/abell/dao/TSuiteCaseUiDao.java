package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.TSuiteCaseUi;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TSuiteCaseUi)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-26 12:30:48
 */
@Repository
public interface TSuiteCaseUiDao extends BaseMapper<TSuiteCaseUi>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TSuiteCaseUi queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TSuiteCaseUi> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tSuiteCaseUi 实例对象
     * @return 对象列表
     */
    List<TSuiteCaseUi> queryAll(TSuiteCaseUi tSuiteCaseUi);

    /**
     * 新增数据
     *
     * @param tSuiteCaseUi 实例对象
     * @return 影响行数
     */
    @Override
    int insert(TSuiteCaseUi tSuiteCaseUi);

    /**
     * 修改数据
     *
     * @param tSuiteCaseUi 实例对象
     * @return 影响行数
     */
    int update(TSuiteCaseUi tSuiteCaseUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    Integer findMaxSortBySuiteId(@Param("suiteId")Long suiteId);

}
