package com.snail.abell.projectPage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.permission.vo.ProjectPageDto;
import com.snail.abell.projectPage.Vo.ProjectPageVo;
import com.snail.abell.projectPage.entity.TProjectPage;
import com.snail.abell.projectPage.service.TProjectPageService;
import com.snail.abell.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.snail.abell.base.ResultCode.PROJECT_NOT_EXIST_ERROR;

/**
 * t_client(TProjectPage)表控制层
 *
 * @author Abell
 * @since 2022-06-05 19:11:34
 */
@Api(tags = "项目页面")
@Validated
@RestController
@ResponseResult
@RequestMapping("/tProjectPage")
public class TProjectPageController {
    /**
     * 服务对象
     */
    @Resource
    private TProjectPageService tProjectPageService;

    @GetMapping("/listPage")
    @ApiOperation("项目页面查询")
    public Page<TProjectPage> getPageList(ProjectPageVo projectPage) {
        Page<TProjectPage> page = new Page<>(projectPage.getPage(), projectPage.getPerPage());
        Page<TProjectPage> projectPageList = tProjectPageService.pageQuery(page, projectPage);
        return projectPageList;
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "通过项目id取页面")
    public List<ProjectPageDto> getlistProjectId(@PathVariable Long id) {
        List<ProjectPageDto> projectPages = tProjectPageService.findDtoByProjectId(id);
        return projectPages;
    }

    @GetMapping("/listById/{id}")
    @ApiOperation(value = "通过id取页面")
    public ProjectPageDto getlistById(@PathVariable Long id) {
        return  this.tProjectPageService.selectDtoByPrimaryKey(id);

    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public boolean savaProjectPage( @RequestBody @Validated TProjectPage projectPage) {
        List<ProjectPageDto> projectPages = tProjectPageService.findDtoByProjectIdAndPageName(projectPage.getProjectId(), projectPage.getPageName());
        if (projectPages.size() > 0) {
            throw new BizException(PROJECT_NOT_EXIST_ERROR);
        }
        projectPage.setCreateBy(SecurityUtils.getCurrentUsername());
        tProjectPageService.insertSelective(projectPage);
        return true;
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public boolean editProjectPage(@RequestBody TProjectPage projectPage) {
        List<ProjectPageDto> projectPages = tProjectPageService.findDtoByProjectIdAndPageNameAndIdNot(projectPage.getProjectId(), projectPage.getPageName(), projectPage.getId());
        if (projectPages.size() > 0) {
            throw new BizException(PROJECT_NOT_EXIST_ERROR);
        }
        projectPage.setUpdateBy(SecurityUtils.getCurrentUsername());
        tProjectPageService.updateByPrimaryKey(projectPage);
        return true;
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "删除")
    public boolean delProjectPage( @PathVariable Long id ) {

        return  tProjectPageService.removeById(id);
    }

    @GetMapping("/copyPageById/{id}")
    @ApiOperation(value = "通过id复制页面")
    public boolean copyPageById(@PathVariable Long id) {
        tProjectPageService.copyPageById(id);
        return true;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TProjectPage selectOne(Long id) {
        return this.tProjectPageService.queryById(id);
    }

}
