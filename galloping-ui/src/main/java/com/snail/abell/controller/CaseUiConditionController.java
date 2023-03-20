package com.snail.abell.controller;

import com.snail.abell.Vo.BaseImag;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.dto.ConditionDto;
import com.snail.abell.entity.CaseUiCondition;
import com.snail.abell.logInterface.Log;
import com.snail.abell.minio.MinioService;
import com.snail.abell.service.CaseUiConditionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Abell
 * @date 2023/2/26
 */

@Api(tags = "条件管理")
@Validated
@RestController
@ResponseResult
@RequestMapping("/conditionManagement")
public class CaseUiConditionController {


    @Resource
    private CaseUiConditionService caseUiConditionService;
    @Resource
    private MinioService minioService;

    @PostMapping("/saveConditions")
    @ApiOperation(value = "保存条件")
    @Log(description = "保存条件")
    public boolean savaProjectPage(@RequestBody CaseUiCondition caseUiCondition) {



      return caseUiConditionService.saveOrUpdateByCondition(caseUiCondition);
    }

    @PostMapping("/fetchConditionByStepId")
    @ApiOperation(value = "查询条件")
    @Log(description = "查询条件")
    public CaseUiCondition fetchConditionByStepId(@RequestBody ConditionDto conditionDto) {

        return caseUiConditionService.fetchConditionByStepId(conditionDto);
    }


    @PostMapping("/saveImag")
    @ApiOperation(value = "保存Base64图片")
    @Log(description = "保存Base64图片")
    public String saveImageByBase(@RequestBody BaseImag baseImag){


        return minioService.saveImageByBase( baseImag.getBaseFile(), "testcase",baseImag.getConditionName() );
    }

}
