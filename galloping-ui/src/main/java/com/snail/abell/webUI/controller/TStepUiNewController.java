package com.snail.abell.webUI.controller;

import com.snail.abell.webUI.entity.TStepUiNew;
import com.snail.abell.webUI.service.TStepUiNewService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TStepUiNew)表控制层
 *
 * @author Abell
 * @since 2022-06-26 12:30:47
 */
@Api(tags = "(TStepUiNew)") 
@Validated
@RestController
@RequestMapping("tStepUiNew")
public class TStepUiNewController {
    /**
     * 服务对象
     */
    @Resource
    private TStepUiNewService tStepUiNewService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TStepUiNew selectOne(Long id) {
        return this.tStepUiNewService.queryById(id);
    }

}