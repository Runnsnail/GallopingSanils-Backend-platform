package com.snail.abell.webUI.dao;

import com.snail.abell.webUI.entity.TPageElement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * t_client(TPageElement)表数据库访问层
 *
 * @author Abell
 * @since 2022-06-06 16:41:20
 */
public interface TPageElementDao extends BaseMapper<TPageElement>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TPageElement queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TPageElement> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tPageElement 实例对象
     * @return 对象列表
     */
    List<TPageElement> queryAll(TPageElement tPageElement);

    /**
     * 新增数据
     *
     * @param tPageElement 实例对象
     * @return 影响行数
     */
    @Override
    int insert(TPageElement tPageElement);

    /**
     * 修改数据
     *
     * @param tPageElement 实例对象
     * @return 影响行数
     */
    int update(TPageElement tPageElement);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<TPageElement> findByPageId(@Param("projectId")Long id);

    void insertList(List<TPageElement> pageElements);
}