package com.snail.abell.controller;

import com.snail.abell.entity.TPlanSuiteUi;
import com.snail.abell.service.TPlanSuiteUiService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TPlanSuiteUi)表控制层
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
@Api(tags = "测试计划")
@Validated
@RestController
@RequestMapping("/tPlanSuiteUi")
public class TPlanSuiteUiController {
    /**
     * 服务对象
     */
    @Resource
    private TPlanSuiteUiService tPlanSuiteUiService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TPlanSuiteUi selectOne(Long id) {
        return this.tPlanSuiteUiService.queryById(id);
    }

}
