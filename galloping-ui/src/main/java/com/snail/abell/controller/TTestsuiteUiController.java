package com.snail.abell.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.Vo.*;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.dto.TestUiDto;
import com.snail.abell.dto.UiExectionDto;
import com.snail.abell.entity.TSuiteCaseUi;
import com.snail.abell.entity.TTestsuiteUi;
import com.snail.abell.exception.BizException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.TTestcaseUiNewService;
import com.snail.abell.service.TTestsuiteUiService;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.utils.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.snail.abell.base.ResultCode.SUITCASE_EXIST_ERROR;

/**
 * (TTestsuiteUi)表控制层
 *
 * @author Abell
 * @since 2022-06-28 15:34:53
 */
@Api(tags = "测试用例控制层")
@Validated
@ResponseResult
@RestController
@RequestMapping("/tTestsuiteUi")
public class TTestsuiteUiController {
    /**
     * 服务对象
     */
    @Resource
    private TTestsuiteUiService testsuiteUiService;

    @Autowired
    private TTestcaseUiNewService testcaseUiNewService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TTestsuiteUi selectOne(Long id) {
        return this.testsuiteUiService.queryById(id);
    }


    /**
     *
     * @param pageSuitsVo
     * @return  分页数据
     */
    @GetMapping("/listTree")
    @ApiOperation(value = "获取tree列表")
    @Log(description = "获取迭代用例集")
    public List<TestSuitUiVo> getPageList(PageSuitsVo pageSuitsVo) {
        List<TestSuitUiVo> suitUiDtoList = testsuiteUiService.pageQuery( pageSuitsVo);
        return TreeUtils.generateTrees(suitUiDtoList);
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "删除")
    @Log(description = "删除用例集")
    public boolean deleteSuits( @PathVariable Long id ) {

        return  testsuiteUiService.removeById(id);
    }

    /**
     *
     * @param id
     * @return 根据项目获取列表
     */
    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    @Log(description = "根据项目获取列表")
    public List<TestSuitUiVo> listByProjectId(@PathVariable long id) {
        List<TestSuitUiVo> suiteUiDtoList = testsuiteUiService.listByProjectId(id);
        return suiteUiDtoList;
    }

    @GetMapping("/fetchSuitList")
    @ApiOperation(value = "获取分页带参运行列表")
    @Log(description="获取分页带参运行列表")
    public IPage<UiExectionDto> getPageList(UiExectionVo uiExectionVo) {
        Page<UiExectionDto> page = new Page<>(uiExectionVo.getPage(), uiExectionVo.getPerPage());
        IPage<UiExectionDto> testcaseUiNewList = testsuiteUiService.pageQueryList(page, uiExectionVo);
        return testcaseUiNewList;
    }

    @PostMapping("/updateEnv")
    @ApiOperation(value = "定时执行时修改环境")
    @Log(description = "定时执行时修改环境")
    public boolean editTestsuiteUi(@RequestBody EnvVo envVo) {


        return testsuiteUiService.updateByUiExection(envVo);
    }



    @GetMapping("/listCaseById/{id}")
    @ApiOperation(value = "获取列表")
    @Log(description = "获取列表")
    public List<TestUiDto> listCaseById(@PathVariable long id) {
        return testcaseUiNewService.selectDtoBySuiteId(id);
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增suite节点")
    @Log(description = "新增suite节点")
    public boolean savaTTestsuiteUi(@RequestBody TestSuitMetaVo suiteUi) throws Exception {
        List<TTestsuiteUi> testsuiteUis = testsuiteUiService.selectByNameAndProjectId(suiteUi.getName(), suiteUi.getProjectId());
        if (testsuiteUis.size() > 0) {
            throw  new BizException(SUITCASE_EXIST_ERROR);
        }
        TTestsuiteUi newTestsuiteUi = new TTestsuiteUi();
        BeanUtils.copyProperties(suiteUi,newTestsuiteUi);
        newTestsuiteUi.setName(suiteUi.getName());
        newTestsuiteUi.setCreateBy(SecurityUtils.getCurrentUsername());
        return testsuiteUiService.saveAndCreatJob(newTestsuiteUi);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑suite节点")
    @Log(description = "编辑suite节点")
    public boolean editTTestsuiteUi(@RequestBody TestSuitMetaVo testsuiteUi) {
        List<TTestsuiteUi> testsuiteUis = testsuiteUiService.selectByNameAndProjectIdAndIdNot(testsuiteUi.getName(), testsuiteUi.getProjectId(), testsuiteUi.getId());
        if (testsuiteUis.size() > 0) {
            throw  new BizException(SUITCASE_EXIST_ERROR);}

        return testsuiteUiService.updateSuit(testsuiteUi);
    }



    @PostMapping("/remove")
    @ApiOperation(value = "删除suite节点")
    @Log(description = "删除suite节点")
    public boolean delTTestsuiteUi(@RequestBody TTestsuiteUi testsuiteUi) {
        return testsuiteUiService.deleteById(testsuiteUi.getId());
    }


    @PostMapping("/updateSuiteTree")
    @ApiOperation(value = "更新SuiteTree排序")
    @Log(description = "更新SuiteTree排序")
    public boolean updateSuiteTree(@RequestBody String treeLists) {
        JSONObject jsonObject = JSONObject.parseObject(treeLists);
        List<SuiteTreeVo> suiteTreeVoList = JSONObject.parseArray(jsonObject.getJSONArray("treeList").toJSONString(),SuiteTreeVo.class);
        return testsuiteUiService.updateSuiteTree(suiteTreeVoList);
    }

    @PostMapping("/updateCaseSort")
    @ApiOperation(value = "更新用例排序")
    @Log(description = "更新用例排序")
    public boolean updateCaseSort(@RequestBody List<TSuiteCaseUi> suiteCaseUis) {

        return testsuiteUiService.updateCaseSort(suiteCaseUis);
    }

    @PostMapping("/addCaseToSuite")
    @ApiOperation(value = "把用例加入用例集")
    @Log(description = "把用例加入用例集")
    public boolean addCaseToBusiness(@RequestBody List<TSuiteCaseUi> suiteCaseUis) {
        return testsuiteUiService.addCaseToSuite(suiteCaseUis);
    }

    @PostMapping("/delSuiteCaseById/{id}")
    public boolean deleteBusinessCaseByCaseId(@PathVariable Long id) {

        return testsuiteUiService.deleteByCaseId(id);
    }

}
