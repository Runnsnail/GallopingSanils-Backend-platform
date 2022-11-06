package com.snail.abell.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.Vo.CaseIdVo;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.dto.TestCaseMetoDto;
import com.snail.abell.dto.TestCasesDto;
import com.snail.abell.dto.TestUiDto;
import com.snail.abell.entity.TTestcaseUiNew;
import com.snail.abell.exception.BizException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.TEnvService;
import com.snail.abell.service.TTestcaseUiNewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.snail.abell.base.ResultCode.TESTSTEP_EDIT_ERROR;

/**
 * (TestcaseUiNew)表控制层
 *
 * @author Abell
 * @since 2022-06-26 11:09:52
 */
@Api(tags = "WebUI测试用例执行")
@Validated
@RestController
@ResponseResult
@RequestMapping("/TestcaseUiNew")
public class TestCaseUiController {


    /**
     * 服务对象
     */
    @Resource
    private TTestcaseUiNewService testcaseUiNewService;


    @Resource
    private TEnvService envService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TTestcaseUiNew selectOne(Long id) {
        return this.testcaseUiNewService.queryById(id);
    }


    @GetMapping("/listCasesPage")
    @ApiOperation(value = "获取分页带参测试用例列表")
    @Log(description="获取分页带参测试用例列表")
    public IPage<TestCasesDto> getPageList(CaseIdVo caseIdVo) {
        Page<TestCasesDto> page = new Page<>(caseIdVo.getPage(), caseIdVo.getPerPage());
        IPage<TestCasesDto> testcaseUiNewList = testcaseUiNewService.pageQuery(page, caseIdVo);
        return testcaseUiNewList;
    }

    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    @Log(description="获取列表")
    public List<TTestcaseUiNew> listByProjectId(@PathVariable long id) {
        List<TTestcaseUiNew> testcaseUiNews = testcaseUiNewService.lambdaQuery().eq(TTestcaseUiNew::getId, id).list();
        return testcaseUiNews;
    }

    @GetMapping("/listCaseById/{id}")
    @ApiOperation(value = "查询测试用例集")
    @Log(description="查询测试用例集")
    public List<TestUiDto> listCaseById(@PathVariable long id) {
        return testcaseUiNewService.selectDtoBySuiteId(id);
    }


    @GetMapping("/allBusiness/{id}")
    @ApiOperation(value = "查询测试业务集")
    @Log(description="查询测试业务集")
    public List<TTestcaseUiNew> getList(@PathVariable Long id) {
        List<TTestcaseUiNew> testcaseUiNews = testcaseUiNewService.lambdaQuery().eq(TTestcaseUiNew::getId, id).
                eq(TTestcaseUiNew::getCaseType, 2L).list();

        return testcaseUiNews;
    }

    @PostMapping("/addOrEdit")
    @ApiOperation(value = "新增测试用例")
    @Log(description="新增测试用例")
    public Boolean addOrEditCases(@RequestBody TestCaseMetoDto caseMeto) {

        return testcaseUiNewService.addOrEditCases(caseMeto);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增测试用例")
    @Log(description="新增测试用例")
    public TTestcaseUiNew savaProject(@RequestBody TestUiDto testcaseUi) {

        return testcaseUiNewService.add(testcaseUi);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑测试用例")
    @Log(description="编辑测试用例")
    public TTestcaseUiNew editProject(@RequestBody TestUiDto testcaseUi) {
        if (testcaseUi.getTestSteps().size() == 0) {
            throw new BizException(TESTSTEP_EDIT_ERROR);
        }
        return testcaseUiNewService.update(testcaseUi);
    }

    @DeleteMapping("/deleteCase/{caseId}")
    @ApiOperation(value = "删除测试用例")
    @Log(description="删除测试用例")
    public boolean delProject(@PathVariable Long caseId) {

        return testcaseUiNewService.deleteById(caseId);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询用例")
    @Log(description="通过id查询用例")
    public TTestcaseUiNew getById(@PathVariable Long id) {
        LambdaQueryWrapper<TTestcaseUiNew> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(TTestcaseUiNew::getId,id).eq(TTestcaseUiNew::getCaseType,1);
        TTestcaseUiNew testcaseUiNew = testcaseUiNewService.getOne(queryWrapper);
        return testcaseUiNew;
    }

    @GetMapping("/business/{id}")
    @ApiOperation(value = "通过id查询业务")
    public TTestcaseUiNew getBusinessById(@PathVariable Long id) {
        LambdaQueryWrapper<TTestcaseUiNew> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(TTestcaseUiNew::getId,id).eq(TTestcaseUiNew::getCaseType,2);
        TTestcaseUiNew testcaseUiNew = testcaseUiNewService.getOne(queryWrapper);
        return testcaseUiNew;
    }

    @GetMapping("/copyCaseById/{id}")
    @ApiOperation(value = "通过id复制页面")
    public boolean copyCaseById(@PathVariable Long id) {
        TTestcaseUiNew  testcaseUiNew = testcaseUiNewService.copyCaseById(id);
        return testcaseUiNew == null;
    }

    @GetMapping("/businessToCase/{id}")
    @ApiOperation(value = "业务转用例")
    public TTestcaseUiNew businesstoCase(@PathVariable Long id) {
        return testcaseUiNewService.businesstoCase(id);
    }

    @GetMapping("/getUiEnv")
    @ApiOperation("查询UI环境信息")
    @Log(description = "查询UI环境信息")
    public ArrayList<HashMap<String, String>> getUiEnv() {
        ArrayList<HashMap<String, String>> envList = envService.getUiEnv();
        return envList;
    }




}
