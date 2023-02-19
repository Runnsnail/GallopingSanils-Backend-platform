package com.snail.abell.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.entity.SeleniumNode;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.SeleniumNodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Abell
 * @date 2023/2/19
 */

@Api(tags = "SeleniumNode地址")
@Validated
@RestController
@ResponseResult
@RequestMapping("/SeleniumNode")
public class SeleniumNodeController {

    @Resource
    private SeleniumNodeService seleniumNodeService;

    @GetMapping("/fetchById/{browser}")
    @ApiOperation(value = "获取SeleniumNode地址")
    @Log(description = "获取SeleniumNode地址")
    public SeleniumNode fetchById(@PathVariable String browser) {

        return seleniumNodeService.lambdaQuery().eq(SeleniumNode::getBrowser,browser).one();
    }
}
