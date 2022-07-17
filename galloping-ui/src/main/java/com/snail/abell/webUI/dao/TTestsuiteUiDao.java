package com.snail.abell.webUI.dao;

import com.snail.abell.webUI.entity.TTestsuiteUi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TTestsuiteUi)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-28 15:34:53
 */
public interface TTestsuiteUiDao extends BaseMapper<TTestsuiteUi>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TTestsuiteUi queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TTestsuiteUi> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tTestsuiteUi 实例对象
     * @return 对象列表
     */
    List<TTestsuiteUi> queryAll(TTestsuiteUi tTestsuiteUi);

    /**
     * 新增数据
     *
     * @param tTestsuiteUi 实例对象
     * @return 影响行数
     */
    @Override
    int insert(TTestsuiteUi tTestsuiteUi);

    /**
     * 修改数据
     *
     * @param tTestsuiteUi 实例对象
     * @return 影响行数
     */
    int update(TTestsuiteUi tTestsuiteUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}