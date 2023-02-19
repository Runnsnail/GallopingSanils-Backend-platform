package com.snail.abell.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.entity.CaseParam;
import com.snail.abell.exception.BizException;
import com.snail.abell.exception.EntityNotFoundException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.CaseParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.snail.abell.base.ResultCode.CASEVARIABLE_EXIST_ERROR;

/**
 * @author Abell
 * @date 2023/2/5
 */

@Api(tags = "用例参数(CaseVariables)")
@Validated
@RestController
@ResponseResult
@RequestMapping("/CaseVariable")
public class CaseVariablesController {

    @Resource
    private CaseParamService caseParamService;

    @PostMapping("/save")
    @ApiOperation(value = "保存用例")
    @Log(description = "保存用例")
    public boolean saveCaseVariables(@RequestBody Map<String, CaseParam> param) {
        CaseParam caseParam = param.get("params");

        if (StringUtils.isEmpty(caseParam.getName()) || StringUtils.isEmpty(caseParam.getValue()) ) {
            throw new EntityNotFoundException(CaseParam.class,"CaseVariable", caseParam.getName());
        }
        List<CaseParam> caseParamList = caseParamService.lambdaQuery().eq(CaseParam::getName,caseParam.getName()).list( );
        if (caseParamList.size() > 0) {
            throw  new BizException(CASEVARIABLE_EXIST_ERROR);
        }
        return caseParamService.save(caseParam);
    }

    @GetMapping("/remove/{id}")
    @ApiOperation(value = "删除")
    @Log(description = "删除")
    public boolean removeCaseVariables(@PathVariable Integer id) {
        return caseParamService.removeById(id);
    }


    @GetMapping("/fetchCaseVariables/{id}")
    @ApiOperation(value = "通过CaseID获取用例变量")
    @Log(description = "通过CaseID获取用例变量")
    public List<CaseParam> getlistProjectId(@PathVariable String id) {
        List<CaseParam> caseParamList = caseParamService.lambdaQuery().eq(CaseParam::getCaseid,id).list();
        return caseParamList;
    }
}
