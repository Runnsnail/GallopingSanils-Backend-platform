package com.snail.abell.controller;

import com.snail.abell.entity.TTestbusinessUi;
import com.snail.abell.service.TTestbusinessUiService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TTestbusinessUi)表控制层
 *
 * @author Abell
 * @since 2022-06-26 16:36:43
 */
@Api(tags = "(TTestbusinessUi)")
@Validated
@RestController
@RequestMapping("tTestbusinessUi")
public class TTestbusinessUiController {
    /**
     * 服务对象
     */
    @Resource
    private TTestbusinessUiService tTestbusinessUiService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TTestbusinessUi selectOne(Long id) {
        return this.tTestbusinessUiService.queryById(id);
    }

}
