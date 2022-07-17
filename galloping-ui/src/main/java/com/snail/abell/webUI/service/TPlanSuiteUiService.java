package com.snail.abell.webUI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.webUI.entity.TPlanSuiteUi;

import java.util.List;

/**
 * (TPlanSuiteUi)表服务接口层
 *
 * @author Abell
 * @since 2022-06-26 16:36:42
 */
public interface TPlanSuiteUiService extends IService<TPlanSuiteUi>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TPlanSuiteUi queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TPlanSuiteUi> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tPlanSuiteUi 实例对象
     * @return 实例对象
     */
    TPlanSuiteUi insert(TPlanSuiteUi tPlanSuiteUi);

    /**
     * 修改数据
     *
     * @param tPlanSuiteUi 实例对象
     * @return 实例对象
     */
    TPlanSuiteUi update(TPlanSuiteUi tPlanSuiteUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}