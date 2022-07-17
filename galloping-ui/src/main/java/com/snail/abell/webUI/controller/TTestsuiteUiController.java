package com.snail.abell.webUI.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.webUI.dto.SuiteUiDto;
import com.snail.abell.webUI.dto.SuiteUiMapper;
import com.snail.abell.webUI.dto.TestUiDto;
import com.snail.abell.webUI.entity.TSuiteCaseUi;
import com.snail.abell.webUI.entity.TTestsuiteUi;
import com.snail.abell.webUI.service.TTestcaseUiNewService;
import com.snail.abell.webUI.service.TTestsuiteUiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping("tTestsuiteUi")
public class TTestsuiteUiController {
    /**
     * 服务对象
     */
    @Resource
    private TTestsuiteUiService testsuiteUiService;
    @Autowired
    private TTestcaseUiNewService testcaseUiNewService;
    private SuiteUiMapper suiteUiMapper;

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
     * @param pageNum
     * @param pageSize
     * @param testcaseUi
     * @return  分页数据
     */
    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public List<SuiteUiDto> getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") TTestsuiteUi testcaseUi) {
        Page<TTestsuiteUi> page = new Page<>(pageNum, pageSize);
        List<TTestsuiteUi> testsuitesList = testsuiteUiService.pageQuery(page, testcaseUi);
        return testsuitesList.stream().map(suiteUiMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return 根据项目获取列表
     */
    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public List<SuiteUiDto> listByProjectId(@PathVariable long id) {
        List<SuiteUiDto> suiteUiDtoList = testsuiteUiService.listByProjectId(id);
        return suiteUiDtoList;
    }

    @GetMapping("/listCaseById/{id}")
    @ApiOperation(value = "获取列表")
    public List<TestUiDto> listCaseById(@PathVariable long id) {
        return testcaseUiNewService.selectDtoBySuiteId(id);
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public boolean savaTTestsuiteUi(@RequestBody TTestsuiteUi suiteUi) {
        List<TTestsuiteUi> testsuiteUis = testsuiteUiService.selectByNameAndProjectId(suiteUi.getName(), suiteUi.getProjectId());
        if (testsuiteUis.size() > 0) {
            throw  new BizException(SUITCASE_EXIST_ERROR);
        }
        suiteUi.setUpdateBy(SecurityUtils.getCurrentUsername());
        suiteUi.setCreateBy(SecurityUtils.getCurrentUsername());
        return testsuiteUiService.save(suiteUi);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public boolean editTTestsuiteUi(@RequestBody TTestsuiteUi testsuiteUi) {
        List<TTestsuiteUi> testsuiteUis = testsuiteUiService.selectByNameAndProjectIdAndIdNot(testsuiteUi.getName(), testsuiteUi.getProjectId(), testsuiteUi.getId());
        if (testsuiteUis.size() > 0) {
            throw  new BizException(SUITCASE_EXIST_ERROR);        }
        testsuiteUi.setUpdateBy(SecurityUtils.getCurrentUsername());
        return testsuiteUiService.updateById(testsuiteUi);
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public boolean delTTestsuiteUi(@RequestBody TTestsuiteUi testsuiteUi) {
        return testsuiteUiService.deleteById(testsuiteUi.getId());
    }

    @PostMapping("/updateCaseSort")
    @ApiOperation(value = "更新用例排序")
    public boolean updateCaseSort(@RequestBody List<TSuiteCaseUi> suiteCaseUis) {

        return testsuiteUiService.updateCaseSort(suiteCaseUis);
    }

    @PostMapping("/addCaseToSuite")
    @ApiOperation(value = "把用例加入用例集")
    public boolean addCaseToBusiness(@RequestBody List<TSuiteCaseUi> suiteCaseUis) {
        return testsuiteUiService.addCaseToSuite(suiteCaseUis);
    }

    @PostMapping("/delSuiteCaseById/{id}")
    public boolean deleteBusinessCaseByCaseId(@PathVariable Long id) {

        return testsuiteUiService.deleteByCaseId(id);
    }

}
