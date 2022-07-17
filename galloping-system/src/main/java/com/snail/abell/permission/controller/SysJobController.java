package com.snail.abell.permission.controller;

import com.snail.abell.permission.entity.SysJob;
import com.snail.abell.permission.service.SysJobService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 岗位(SysJob)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "执行任务")
@Validated
@RestController
@RequestMapping("sysJob")
public class SysJobController {
    /**
     * 服务对象
     */
    @Resource
    private SysJobService sysJobService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysJob selectOne(Long id) {
        return this.sysJobService.queryById(id);
    }

}