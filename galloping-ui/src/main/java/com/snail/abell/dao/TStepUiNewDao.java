package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.TStepUiNew;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TStepUiNew)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-26 12:30:45
 */
@Repository
public interface TStepUiNewDao extends BaseMapper<TStepUiNew>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TStepUiNew queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TStepUiNew> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tStepUiNew 实例对象
     * @return 对象列表
     */
    List<TStepUiNew> queryAll(TStepUiNew tStepUiNew);

    /**
     * 新增数据
     *
     * @param tStepUiNew 实例对象
     * @return 影响行数
     */
    int insert(TStepUiNew tStepUiNew);

    /**
     * 修改数据
     *
     * @param tStepUiNew 实例对象
     * @return 影响行数
     */
    int update(TStepUiNew tStepUiNew);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
