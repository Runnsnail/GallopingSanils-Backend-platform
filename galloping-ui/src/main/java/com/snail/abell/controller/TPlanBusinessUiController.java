package com.snail.abell.controller;

import com.snail.abell.entity.TPlanBusinessUi;
import com.snail.abell.service.TPlanBusinessUiService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TPlanBusinessUi)表控制层
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
@Api(tags = "业务计划UI")
@Validated
@RestController
@RequestMapping("/PlanBusinessUi")
public class TPlanBusinessUiController {
    /**
     * 服务对象
     */
    @Resource
    private TPlanBusinessUiService tPlanBusinessUiService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TPlanBusinessUi selectOne(Long id) {
        return this.tPlanBusinessUiService.queryById(id);
    }

}
