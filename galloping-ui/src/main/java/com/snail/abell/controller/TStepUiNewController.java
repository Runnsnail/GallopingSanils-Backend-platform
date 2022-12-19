package com.snail.abell.controller;

import com.snail.abell.Vo.CaseStepVo;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.dto.StepInforDto;
import com.snail.abell.dto.StepMessageDto;
import com.snail.abell.entity.TStepUiNew;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.TStepUiNewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TStepUiNew)表控制层
 *
 * @author Abell
 * @since 2022-06-26 12:30:47
 */
@Api(tags = "(UI测试步骤)")
@Validated
@RestController
@ResponseResult
@RequestMapping("/StepUiNew")
public class TStepUiNewController {
    /**
     * 服务对象
     */
    @Resource
    private TStepUiNewService tStepUiNewService;


    @PostMapping("/addCaseStep")
    @ApiOperation(value = "修改測試步骤")
    @Log(description = "修改測試步骤")
    public String addCaseStep(@RequestBody CaseStepVo caseStepVo){
        return tStepUiNewService.addCaseStep(caseStepVo);
    }

    @PostMapping("/addCaseSteps")
    @ApiOperation(value = "修改新增測試步骤集")
    @Log(description = "修改新增測試步骤集")
    public Boolean addCaseSteps(@RequestBody List<TStepUiNew> stepsList){
        return tStepUiNewService.saveOrUpdateBatch(stepsList);
    }

    @PostMapping("/saveCaseSteps")
    @ApiOperation(value = "修改測試步骤集排序")
    @Log(description = "修改測試步骤集排序")
    public Boolean saveCaseSteps(@RequestBody List<TStepUiNew> stepsList){
        return tStepUiNewService.updateSortBatch(stepsList);
    }

    @DeleteMapping("/deleteStep/{id}")
    @ApiOperation(value = "删除测试步骤")
    @Log(description = "删除测试步骤")
    public Boolean deleteStep( @PathVariable Integer id){
        return tStepUiNewService.removeById(id);
    }

    @GetMapping("/fetchCaseSteps/{caseId}")
    @ApiOperation(value = "通过测试用例id取步骤")
    @Log(description = "通过项目id取页面")
    public List<TStepUiNew> fetchCaseSteps(@PathVariable String caseId) {

        return tStepUiNewService.getStepsByCaseId(caseId);
    }


    @GetMapping("/fetchStepById/{stepId}")
    @ApiOperation(value = "通过测试用例id取步骤详细信息")
    @Log(description = "通过测试用例id取步骤详细信息")
    public StepMessageDto fetchStepById(@PathVariable Integer stepId) {

        return tStepUiNewService.getStepById(stepId);
    }

    @PostMapping("/saveStepInformation")
    @ApiOperation(value = "保存步骤详细信息")
    @Log(description = "保存步骤详细信息")
    public Boolean saveStepInformation(@RequestBody StepInforDto stepInforDto){
        TStepUiNew stepUiNew = new TStepUiNew();
        BeanUtils.copyProperties(stepInforDto,stepUiNew);
        return tStepUiNewService.saveOrUpdate(stepUiNew);
    }

}
