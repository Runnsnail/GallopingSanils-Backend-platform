package com.snail.abell.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.permission.entity.SysQuartzLog;

import java.util.List;

/**
 * 定时任务日志(SysQuartzLog)表服务接口层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
public interface SysQuartzLogService extends IService<SysQuartzLog>{
  /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    SysQuartzLog queryById(Long logId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysQuartzLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysQuartzLog 实例对象
     * @return 实例对象
     */
    SysQuartzLog insert(SysQuartzLog sysQuartzLog);

    /**
     * 修改数据
     *
     * @param sysQuartzLog 实例对象
     * @return 实例对象
     */
    SysQuartzLog update(SysQuartzLog sysQuartzLog);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    boolean deleteById(Long logId);
}