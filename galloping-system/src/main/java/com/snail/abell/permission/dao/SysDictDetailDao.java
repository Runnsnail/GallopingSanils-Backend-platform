package com.snail.abell.permission.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.permission.entity.SysDictDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典详情(SysDictDetail)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Repository
public interface SysDictDetailDao extends BaseMapper<SysDictDetail>{

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    SysDictDetail queryById(Long detailId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysDictDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysDictDetail 实例对象
     * @return 对象列表
     */
    List<SysDictDetail> queryAll(SysDictDetail sysDictDetail);

    /**
     * 新增数据
     *
     * @param sysDictDetail 实例对象
     * @return 影响行数
     */
    @Override
    int insert(SysDictDetail sysDictDetail);

    /**
     * 修改数据
     *
     * @param sysDictDetail 实例对象
     * @return 影响行数
     */
    int update(SysDictDetail sysDictDetail);

    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 影响行数
     */
    int deleteById(Long detailId);

}