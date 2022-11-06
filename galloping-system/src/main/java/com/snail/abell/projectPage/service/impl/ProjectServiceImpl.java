package com.snail.abell.projectPage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.projectPage.Vo.ProjectVo;
import com.snail.abell.projectPage.dao.ProjectMapper;
import com.snail.abell.projectPage.entity.Project;
import com.snail.abell.projectPage.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Abell
 * @date  2022/9/19
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private ProjectService projectService;

    @Override
    public int updateBatch(List<Project> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Project> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Project> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(Project record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(Project record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Page<Project> pageQuery(Page<Project> page, ProjectVo projectVo) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("name");
        Page<Project> listPage;
        if ("Featured".equals(projectVo.getSortBy())) {
            queryWrapper.eq("card_title",projectVo.getCardTitle());
            queryWrapper.like("name",projectVo.getQ());
            listPage = projectMapper.selectPage(page,queryWrapper);
        }else {
            queryWrapper.eq("card_title",projectVo.getCardTitle());
            queryWrapper.like("name",projectVo.getQ());
            queryWrapper.eq("level",projectVo.getSortBy());
            listPage = projectMapper.selectPage(page,queryWrapper);
        }
        return listPage;
    }

    @Override
    public ArrayList<HashMap<String, String>> getProjectNameList() {
        ArrayList<HashMap<String, String>> projectMapArrayList = new ArrayList<HashMap<String, String>>();
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        List<Project> menuList = projectService.list(queryWrapper);
        for (Project project:menuList) {
            HashMap<String, String> map = new HashMap<>();
            map.put("label",project.getName());
            map.put("value",project.getId().toString());
            projectMapArrayList.add(map);
        }
        return projectMapArrayList;
    }
}
