package com.snail.abell.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.DebugTestCaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Abell
 * @date 2022/12/11
 */

@Api(tags = "页面元素编辑(SeleniumGrid)")
@Validated
@RestController
@ResponseResult
@RequestMapping("/SeleniumGrid")
public class SeleniumGridController {

    @Resource
    private DebugTestCaseService debugTestCaseService;

    @PostMapping("/setUpNode")
    @ApiOperation(value = "debugger测试用例")
    @Log(description = "debugger测试用例")
    public boolean setUp(Map<String,String> content){

    return debugTestCaseService.runTestCase(content);
    }

}
