package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.entity.SysQuartzJob;

import java.util.List;

/**
 * 定时任务(SysQuartzJob)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysQuartzJobService extends IService<SysQuartzJob>{
  /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    SysQuartzJob queryById(Long jobId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysQuartzJob> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysQuartzJob 实例对象
     * @return 实例对象
     */
    SysQuartzJob insert(SysQuartzJob sysQuartzJob);

    /**
     * 修改数据
     *
     * @param sysQuartzJob 实例对象
     * @return 实例对象
     */
    SysQuartzJob update(SysQuartzJob sysQuartzJob);

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    boolean deleteById(Long jobId);
}