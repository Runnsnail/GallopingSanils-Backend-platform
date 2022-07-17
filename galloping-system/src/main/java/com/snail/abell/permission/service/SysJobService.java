package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.entity.SysJob;

import java.util.List;

/**
 * 岗位(SysJob)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysJobService extends IService<SysJob>{
  /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    SysJob queryById(Long jobId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
     List<SysJob> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    SysJob insert(SysJob sysJob);

    /**
     * 修改数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    SysJob update(SysJob sysJob);

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    boolean deleteById(Long jobId);
}