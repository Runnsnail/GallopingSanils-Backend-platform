package com.snail.abell.webUI.dao;

import com.snail.abell.webUI.entity.TPlanSuiteUi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TPlanSuiteUi)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-26 16:36:42
 */
public interface TPlanSuiteUiDao extends BaseMapper<TPlanSuiteUi>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TPlanSuiteUi queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TPlanSuiteUi> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tPlanSuiteUi 实例对象
     * @return 对象列表
     */
    List<TPlanSuiteUi> queryAll(TPlanSuiteUi tPlanSuiteUi);

    /**
     * 新增数据
     *
     * @param tPlanSuiteUi 实例对象
     * @return 影响行数
     */
    int insert(TPlanSuiteUi tPlanSuiteUi);

    /**
     * 修改数据
     *
     * @param tPlanSuiteUi 实例对象
     * @return 影响行数
     */
    int update(TPlanSuiteUi tPlanSuiteUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}