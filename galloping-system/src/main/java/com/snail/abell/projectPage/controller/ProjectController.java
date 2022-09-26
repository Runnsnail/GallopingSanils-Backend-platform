package com.snail.abell.projectPage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.logInterface.Log;
import com.snail.abell.projectPage.Vo.ProjectVo;
import com.snail.abell.projectPage.entity.Project;
import com.snail.abell.projectPage.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Abell
 * @date 2022/9/17
 */
@Api(tags = "项目页面")
@RestController
@ResponseResult
@RequestMapping("/ProjectPage")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @GetMapping("/ProjectsPageList")
    @ApiOperation("项目页面查询")
    @Log(description = "项目页面查询")
    public Page<Project> getPageList( ProjectVo projectVo) {
        Page<Project> page = new Page<>(projectVo.getPage(), projectVo.getPerPage());
        return projectService.pageQuery(page, projectVo);
    }

    @GetMapping("/ProjectId/{productId}")
    @ApiOperation(value = "通过项目id取页面")
    @Log(description = "通过项目id取页面")
    public Project getProjectId(@PathVariable Integer productId) {
        return projectService.getById(productId);
    }

    @PostMapping("/saveProject")
    @ApiOperation(value = "保存项目信息")
    @Log(description = "保存项目信息")
    public boolean saveProject(@RequestBody Project projectData) {

        return projectService.saveOrUpdate(projectData);
    }
}
