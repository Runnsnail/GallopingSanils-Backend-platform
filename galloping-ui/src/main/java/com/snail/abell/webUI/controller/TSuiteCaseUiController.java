package com.snail.abell.webUI.controller;

import com.snail.abell.webUI.entity.TSuiteCaseUi;
import com.snail.abell.webUI.service.TSuiteCaseUiService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TSuiteCaseUi)表控制层
 *
 * @author Abell
 * @since 2022-06-26 12:30:48
 */
@Api(tags = "(TSuiteCaseUi)") 
@Validated
@RestController
@RequestMapping("tSuiteCaseUi")
public class TSuiteCaseUiController {
    /**
     * 服务对象
     */
    @Resource
    private TSuiteCaseUiService tSuiteCaseUiService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TSuiteCaseUi selectOne(Long id) {
        return this.tSuiteCaseUiService.queryById(id);
    }

}