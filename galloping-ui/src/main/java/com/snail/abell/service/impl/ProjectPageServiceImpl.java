package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dao.ProjectPageDao;
import com.snail.abell.entity.ProjectPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * t_client(TProjectPage)表服务实现类
 *
 * @author Abell
 * @since 2022-06-05 19:11:34
 */
@Service
public class ProjectPageServiceImpl extends ServiceImpl<ProjectPageDao, ProjectPage> implements com.snail.abell.service.ProjectPageService {
    @Resource
    private ProjectPageDao projectPageDao;

    @Resource
    private com.snail.abell.service.ProjectPageService ProjectPageService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProjectPage queryById(Long id) {
        return this.projectPageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ProjectPage> queryAllByLimit(int offset, int limit) {
        return this.projectPageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param projectPage 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectPage insert(ProjectPage projectPage) {
        this.projectPageDao.insert(projectPage);
        return projectPage;
    }

    /**
     * 修改数据
     *
     * @param projectPage 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectPage update(ProjectPage projectPage) {
        this.projectPageDao.update(projectPage);
        return this.queryById(projectPage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.projectPageDao.deleteById(id) > 0;
    }

    @Override
    public ArrayList<HashMap<String, String>> getPageNameList() {
        return null;
    }

}


