package com.snail.abell.webUI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.webUI.entity.TPlanBusinessUi;

import java.util.List;

/**
 * (TPlanBusinessUi)表服务接口层
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
public interface TPlanBusinessUiService extends IService<TPlanBusinessUi>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TPlanBusinessUi queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TPlanBusinessUi> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tPlanBusinessUi 实例对象
     * @return 实例对象
     */
    TPlanBusinessUi insert(TPlanBusinessUi tPlanBusinessUi);

    /**
     * 修改数据
     *
     * @param tPlanBusinessUi 实例对象
     * @return 实例对象
     */
    TPlanBusinessUi update(TPlanBusinessUi tPlanBusinessUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}