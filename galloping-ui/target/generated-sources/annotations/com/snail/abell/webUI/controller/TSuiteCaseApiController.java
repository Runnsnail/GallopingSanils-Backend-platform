package com.snail.abell.webUI.controller;

import com.snail.abell.webUI.entity.TSuiteCaseApi;
import com.snail.abell.webUI.service.TSuiteCaseApiService;
import com.github.pagehelper.PageInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * (TSuiteCaseApi)表控制层
 *
 * @author Abell
 * @since 2022-06-26 19:19:37
 */
@Api(tags = "(TSuiteCaseApi)") 
@Validated
@RestController
@RequestMapping("tSuiteCaseApi")
public class TSuiteCaseApiController {
    /**
     * 服务对象
     */
    @Resource
    private TSuiteCaseApiService tSuiteCaseApiService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TSuiteCaseApi selectOne(Long id) {
        return this.tSuiteCaseApiService.queryById(id);
    }

}