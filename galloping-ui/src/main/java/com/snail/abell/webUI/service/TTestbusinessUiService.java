package com.snail.abell.webUI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.webUI.entity.TTestbusinessUi;

import java.util.List;

/**
 * (TTestbusinessUi)表服务接口层
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
public interface TTestbusinessUiService extends IService<TTestbusinessUi>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TTestbusinessUi queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TTestbusinessUi> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tTestbusinessUi 实例对象
     * @return 实例对象
     */
    TTestbusinessUi insert(TTestbusinessUi tTestbusinessUi);

    /**
     * 修改数据
     *
     * @param tTestbusinessUi 实例对象
     * @return 实例对象
     */
    TTestbusinessUi update(TTestbusinessUi tTestbusinessUi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}