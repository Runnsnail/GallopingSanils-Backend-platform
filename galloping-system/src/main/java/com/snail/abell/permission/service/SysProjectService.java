package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.entity.SysProject;

import java.util.List;

/**
 * (SysProject)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 16:15:32
 */
public interface SysProjectService extends IService<SysProject>{
  /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysProject queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysProject> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysProject 实例对象
     * @return 实例对象
     */
    SysProject insert(SysProject sysProject);

    /**
     * 修改数据
     *
     * @param sysProject 实例对象
     * @return 实例对象
     */
    SysProject update(SysProject sysProject);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);



}