package com.snail.abell.projectPage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dao.TPageElementDao;
import com.snail.abell.entity.TPageElement;
import com.snail.abell.permission.dto.ProjectPageDtoMapper;
import com.snail.abell.permission.vo.ProjectPageDto;
import com.snail.abell.projectPage.Vo.ProjectPageVo;
import com.snail.abell.projectPage.dao.TProjectPageDao;
import com.snail.abell.projectPage.entity.TProjectPage;
import com.snail.abell.projectPage.service.TProjectPageService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TProjectPageServiceImpl extends ServiceImpl<TProjectPageDao, TProjectPage> implements TProjectPageService {
    @Resource
    private TProjectPageDao tProjectPageDao;
    @Resource
    private TPageElementDao tPageElementDao;
    @Autowired
    private ProjectPageDtoMapper projectPageDtoMapper;
    @Resource
    private TProjectPageService ProjectPageService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TProjectPage queryById(Long id) {
        return this.tProjectPageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TProjectPage> queryAllByLimit(int offset, int limit) {
        return this.tProjectPageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tProjectPage 实例对象
     * @return 实例对象
     */
    @Override
    public TProjectPage insert(TProjectPage tProjectPage) {
        this.tProjectPageDao.insert(tProjectPage);
        return tProjectPage;
    }

    /**
     * 修改数据
     *
     * @param tProjectPage 实例对象
     * @return 实例对象
     */
    @Override
    public TProjectPage update(TProjectPage tProjectPage) {
        this.tProjectPageDao.updateById(tProjectPage);
        return this.queryById(tProjectPage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tProjectPageDao.deleteById(id) > 0;
    }

    @Override
    public void insertSelective(TProjectPage projectPage) {
        tProjectPageDao.insertSelective(projectPage);
    }

    @Override
    public void updateByPrimaryKey(TProjectPage projectPage) {
        tProjectPageDao.updateByPrimaryKey(projectPage);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        tProjectPageDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void copyPageById(Long id) {
        TProjectPage projectPage = tProjectPageDao.queryById(id);
        if (projectPage == null) {
            throw new IllegalArgumentException("该页面元素已删除");
        }
        String newPageName = generateNewPageName(projectPage.getProjectId(), projectPage.getPageName());
        projectPage.setPageName(newPageName);
        tProjectPageDao.insertSelective(projectPage);
        List<TPageElement> pageElements = tPageElementDao.findByPageId(id);
        if (CollectionUtils.isNotEmpty(pageElements)) {
            for (TPageElement pageElement : pageElements) {
                pageElement.setId(null);
                pageElement.setPageId(projectPage.getId());
            }
            tPageElementDao.insertList(pageElements);
        }
    }

    @Override
    public Page<TProjectPage> pageQuery(Page<TProjectPage> page, ProjectPageVo projectPage) {
        QueryWrapper<TProjectPage> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        if (StringUtils.isEmpty(String.valueOf(projectPage.getProjectId()))) {
            projectPage.setProjectId(2);
        }
        queryWrapper.eq("project_id", projectPage.getProjectId());
        if (StringUtils.isNotEmpty(projectPage.getQ())) {
            queryWrapper.like("page_name", projectPage.getQ());
        }

        return tProjectPageDao.selectPage(page, queryWrapper);
    }

    @Override
    public List<ProjectPageDto> findDtoByProjectId(Long id) {
        return this.projectPageDtoMapper.findDtoByProjectId(id);
    }

    @Override
    public ProjectPageDto selectDtoByPrimaryKey(Long id) {
        return this.projectPageDtoMapper.selectDtoByPrimaryKey(id);
    }

    @Override
    public List<ProjectPageDto> findDtoByProjectIdAndPageName(Long projectId, String pageName) {
        return this.projectPageDtoMapper.findDtoByProjectIdAndPageName(projectId, pageName);
    }


    @Override
    public List<ProjectPageDto> findDtoByProjectIdAndPageNameAndIdNot(Long projectId, String pageName, Long id) {
        return this.projectPageDtoMapper.findDtoByProjectIdAndPageNameAndIdNot(projectId, pageName, id);
    }

    @Override
    public ArrayList<HashMap<String, String>> getPageNameList() {
        ArrayList<HashMap<String, String>> pageNameList = new ArrayList<HashMap<String, String>>();
        List<TProjectPage> pageList = ProjectPageService.list();
        for (TProjectPage pageElement:pageList) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value",pageElement.getId().toString());
            map.put("text",pageElement.getPageName());
            pageNameList.add(map);
        }
        return pageNameList;
    }

    private String generateNewPageName(Long projectId, String pageName) {
        int i = 1;
        while (true) {
            pageName = pageName + "_temp";
            List<TProjectPage> byProjectIdAndPageName = tProjectPageDao.findByProjectIdAndPageName(projectId, pageName);
            if (CollectionUtil.isEmpty(byProjectIdAndPageName)) {
                return pageName;
            }
            i++;
            if (i >= 10) {
                return pageName + IdUtil.simpleUUID();
            }
        }
    }
}


