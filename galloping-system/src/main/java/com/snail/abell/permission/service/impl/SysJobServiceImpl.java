package com.snail.abell.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.entity.SysJob;
import com.snail.abell.permission.dao.SysJobDao;
import com.snail.abell.permission.service.SysJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 岗位(SysJob)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobDao,SysJob> implements SysJobService {
    @Resource
    private SysJobDao sysJobDao;

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    @Override
    public SysJob queryById(Long jobId) {
        return this.sysJobDao.queryById(jobId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysJob> queryAllByLimit(int offset, int limit) {
        return this.sysJobDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysJob insert(SysJob sysJob) {
        this.sysJobDao.insert(sysJob);
        return sysJob;
    }

    /**
     * 修改数据
     *
     * @param sysJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysJob update(SysJob sysJob) {
        this.sysJobDao.update(sysJob);
        return this.queryById(sysJob.getJobId());
    }

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long jobId) {
        return this.sysJobDao.deleteById(jobId) > 0;
    }
}