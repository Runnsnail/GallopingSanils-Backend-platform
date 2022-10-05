package com.snail.abell.dao;

import com.snail.abell.entity.TTestbusinessUi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TTestbusinessUi)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
public interface TTestbusinessUiDao extends BaseMapper<TTestbusinessUi>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TTestbusinessUi queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TTestbusinessUi> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tTestbusinessUi 实例对象
     * @return 对象列表
     */
    List<TTestbusinessUi> queryAll(TTestbusinessUi tTestbusinessUi);

    /**
     * 新增数据
     *
     * @param tTestbusinessUi 实例对象
     * @return 影响行数
     */
    int insert(TTestbusinessUi tTestbusinessUi);

    /**
     * 修改数据
     *
     * @param tTestbusinessUi 实例对象
     * @return 影响行数
     */
    int update(TTestbusinessUi tTestbusinessUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
