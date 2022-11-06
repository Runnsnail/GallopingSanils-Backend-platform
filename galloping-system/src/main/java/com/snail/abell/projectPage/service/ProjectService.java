package com.snail.abell.projectPage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.projectPage.Vo.ProjectVo;
import com.snail.abell.projectPage.entity.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Abell
 * @date  2022/9/19
 */
public interface ProjectService extends IService<Project>{


    int updateBatch(List<Project> list);

    int updateBatchSelective(List<Project> list);

    int batchInsert(List<Project> list);

    int insertOrUpdate(Project record);

    int insertOrUpdateSelective(Project record);

    Page<Project> pageQuery(Page<Project> page, ProjectVo projectVo);

    ArrayList<HashMap<String, String>> getProjectNameList();
}
