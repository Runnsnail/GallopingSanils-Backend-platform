package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.entity.SysDict;

import java.util.List;

/**
 * 数据字典(SysDict)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysDictService extends IService<SysDict>{
  /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    SysDict queryById(Long dictId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysDict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysDict 实例对象
     * @return 实例对象
     */
    SysDict insert(SysDict sysDict);

    /**
     * 修改数据
     *
     * @param sysDict 实例对象
     * @return 实例对象
     */
    SysDict update(SysDict sysDict);

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    boolean deleteById(Long dictId);
}