package com.snail.abell.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.entity.SysQuartzLog;
import com.snail.abell.permission.dao.SysQuartzLogDao;
import com.snail.abell.permission.service.SysQuartzLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务日志(SysQuartzLog)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Service
public class SysQuartzLogServiceImpl extends ServiceImpl<SysQuartzLogDao,SysQuartzLog> implements SysQuartzLogService {
    @Resource
    private SysQuartzLogDao sysQuartzLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    @Override
    public SysQuartzLog queryById(Long logId) {
        return this.sysQuartzLogDao.queryById(logId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysQuartzLog> queryAllByLimit(int offset, int limit) {
        return this.sysQuartzLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysQuartzLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysQuartzLog insert(SysQuartzLog sysQuartzLog) {
        this.sysQuartzLogDao.insert(sysQuartzLog);
        return sysQuartzLog;
    }

    /**
     * 修改数据
     *
     * @param sysQuartzLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysQuartzLog update(SysQuartzLog sysQuartzLog) {
        this.sysQuartzLogDao.updateById(sysQuartzLog);
        return this.queryById(sysQuartzLog.getLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long logId) {
        return this.sysQuartzLogDao.deleteById(logId) > 0;
    }
}
