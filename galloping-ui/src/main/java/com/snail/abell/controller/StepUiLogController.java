package com.snail.abell.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.entity.StepUiLog;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.StepUiLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Abell
 * @date 2023/2/12
 */

@Api(tags = "测试步骤详细日志")
@Validated
@RestController
@ResponseResult
@RequestMapping("/stepUiLog")
public class StepUiLogController {

    @Resource
    private StepUiLogService stepUiLogService;

    @GetMapping("/listByCaseId/{id}")
    @ApiOperation(value = "通过用例id取测试步骤日志")
    @Log(description = "通过用例id取测试步骤日志")
    public List<StepUiLog> getlistProjectId(@PathVariable String id) {

        List<StepUiLog> stepUiLogs = stepUiLogService.lambdaQuery().eq(StepUiLog::getCaseId,id).list();

        List<StepUiLog> sortLogs = stepUiLogs.stream().sorted(Comparator.comparing(StepUiLog ::getEndFlag)).collect(Collectors.toList());

        return sortLogs;
    }
}
