package com.snail.abell.permission.controller;

import com.snail.abell.permission.entity.SysQuartzLog;
import com.snail.abell.permission.service.SysQuartzLogService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 定时任务日志(SysQuartzLog)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "定时任务日志")
@Validated
@RestController
@RequestMapping("/sysQuartzLog")
public class SysQuartzLogController {
    /**
     * 服务对象
     */
    @Resource
    private SysQuartzLogService sysQuartzLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysQuartzLog selectOne(Long id) {
        return this.sysQuartzLogService.queryById(id);
    }

}