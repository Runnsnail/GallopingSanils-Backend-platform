package com.snail.abell.permission.controller;

import com.snail.abell.permission.entity.SysUsersJobs;
import com.snail.abell.permission.service.SysUsersJobsService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (SysUsersJobs)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "用户任务关联")
@Validated
@RestController
@RequestMapping("sysUsersJobs")
public class SysUsersJobsController {
    /**
     * 服务对象
     */
    @Resource
    private SysUsersJobsService sysUsersJobsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUsersJobs selectOne(Long id) {
        return this.sysUsersJobsService.queryById(id);
    }

}