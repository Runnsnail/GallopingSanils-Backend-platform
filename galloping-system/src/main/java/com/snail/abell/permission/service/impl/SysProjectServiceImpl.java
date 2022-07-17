package com.snail.abell.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.dao.SysProjectDao;
import com.snail.abell.permission.dto.ProjectPageDtoMapper;
import com.snail.abell.permission.entity.SysProject;
import com.snail.abell.permission.service.SysProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysProject)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 16:15:32
 */
@Service
public class SysProjectServiceImpl extends ServiceImpl<SysProjectDao,SysProject> implements SysProjectService {
    @Resource
    private SysProjectDao sysProjectDao;

    @Resource
    private ProjectPageDtoMapper projectPageDtoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysProject queryById(Long id) {
        return this.sysProjectDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysProject> queryAllByLimit(int offset, int limit) {
        return this.sysProjectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysProject 实例对象
     * @return 实例对象
     */
    @Override
    public SysProject insert(SysProject sysProject) {
        this.sysProjectDao.insert(sysProject);
        return sysProject;
    }

    /**
     * 修改数据
     *
     * @param sysProject 实例对象
     * @return 实例对象
     */
    @Override
    public SysProject update(SysProject sysProject) {
        this.sysProjectDao.update(sysProject);
        return this.queryById(sysProject.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysProjectDao.deleteById(id) > 0;
    }




    @Override
    public boolean save(SysProject entity) {
        return super.save(entity);
    }
}