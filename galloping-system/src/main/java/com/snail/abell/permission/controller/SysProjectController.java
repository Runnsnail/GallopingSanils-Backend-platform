package com.snail.abell.permission.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.permission.dao.SysProjectDao;
import com.snail.abell.permission.entity.SysProject;
import com.snail.abell.permission.service.SysProjectService;
import com.snail.abell.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.snail.abell.base.ResultCode.PROJECT_EXIST_ERROR;

/**
 * (SysProject)表控制层
 *
 * @author Abell
 * @since 2022-06-05 16:15:32
 */
@Api(tags = "基础项目")
@Validated
@ResponseResult
@RestController
@RequestMapping("/sysProject")
public class SysProjectController {
    /**
     * 服务对象
     */
    @Resource
    private SysProjectService sysProjectService;

    @Autowired
    private SysProjectDao sysProjectDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysProject selectOne(Long id) {
        return this.sysProjectService.queryById(id);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public List<SysProject> getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize) {
        Page<SysProject> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysProject> queryWrapper = new QueryWrapper<>();
        //查询分页数据
        return this.sysProjectDao.selectPage(page, queryWrapper).getRecords();

    }

    @PostMapping("/add")
    public boolean savaProject(@RequestBody SysProject project) {

        List<SysProject> projects = sysProjectService.lambdaQuery().eq(SysProject::getProjectName,project.getProjectName()).list();
        if (projects.size() > 0) {
            throw new BizException(PROJECT_EXIST_ERROR);
        }
        project.setCreateBy(SecurityUtils.getCurrentUsername());
        project.setUpdateBy(SecurityUtils.getCurrentUsername());
        return sysProjectService.save(project);
    }

    @PutMapping("/edit")
    public boolean editProject(@RequestBody SysProject project) {
        List<SysProject> projects = sysProjectService.lambdaQuery().eq(SysProject::getProjectName,project.getProjectName()).
                eq(SysProject::getId,project.getId()).list( );
        if (projects.size() > 0) {
            throw new BizException(PROJECT_EXIST_ERROR);
        }
        project.setUpdateBy(SecurityUtils.getCurrentUsername());
        return sysProjectService.updateById(project);
    }

    @PostMapping("/remove")
    public boolean delProject(@RequestBody SysProject project) {
        return  sysProjectService.removeById(project.getId());
    }

}