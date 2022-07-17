package com.snail.abell.webUI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.webUI.entity.TSuiteCaseUi;

import java.util.List;

/**
 * (TSuiteCaseUi)表服务接口层
 *
 * @author Abell
 * @since 2022-06-26 12:30:48
 */
public interface TSuiteCaseUiService extends IService<TSuiteCaseUi>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TSuiteCaseUi queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TSuiteCaseUi> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tSuiteCaseUi 实例对象
     * @return 实例对象
     */
    TSuiteCaseUi insert(TSuiteCaseUi tSuiteCaseUi);

    /**
     * 修改数据
     *
     * @param tSuiteCaseUi 实例对象
     * @return 实例对象
     */
    TSuiteCaseUi update(TSuiteCaseUi tSuiteCaseUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}