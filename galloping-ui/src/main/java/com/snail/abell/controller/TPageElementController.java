package com.snail.abell.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.Vo.EditElementVo;
import com.snail.abell.Vo.ElementVo;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.entity.TPageElement;
import com.snail.abell.exception.BizException;
import com.snail.abell.exception.EntityNotFoundException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.service.TPageElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.snail.abell.base.ResultCode.PAGEELEMENT_EXIST_ERROR;

/**
 * t_client(TPageElement)表控制层
 *
 * @author Abell
 * @since 2022-06-06 16:41:25
 */
@Api(tags = "页面元素编辑(TPageElement)")
@Validated
@RestController
@ResponseResult
@RequestMapping("/PageElement")
public class TPageElementController {
    /**
     * 服务对象
     */
    @Resource
    private TPageElementService pageElementService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TPageElement selectOne(Integer id) {
        return this.pageElementService.queryById(id);
    }


    /**
     *
     * @param elementVo
     * @return 分页数据
     */
    @GetMapping("/listPageElement")
    @ApiOperation(value = "获取分页带参列表")
    @Log(description = "获取分页带参列表")
    public Page<TPageElement> getListPageElement( ElementVo elementVo){

        Page<TPageElement> page = new Page<>(elementVo.getPage(), elementVo.getPerPage());
        return pageElementService.pageQuery(page, elementVo);
    }


    @GetMapping("/listByElementId/{id}")
    @ApiOperation(value = "通过页面id取页面")
    @Log(description = "通过页面id取页面")
    public List<TPageElement> getlistProjectId(@PathVariable Long id) {
        List<TPageElement> projectPages = pageElementService.lambdaQuery().eq(TPageElement::getPageId,id).list();
        return projectPages;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @Log(description = "新增")
    public boolean savaPageElement(@RequestBody TPageElement pageElement) {
        if (StringUtils.isEmpty(pageElement.getElementName()) || StringUtils.isEmpty(pageElement.getByType()) || StringUtils.isEmpty(pageElement.getByValue())) {
            throw new EntityNotFoundException(TPageElement.class,"ElementName",pageElement.getElementName());
        }
        List<TPageElement> pageElements = pageElementService.lambdaQuery().eq(TPageElement::getElementName,pageElement.getElementName()).
                eq(TPageElement::getPageId,pageElement.getPageId()).list( );
        if (pageElements.size() > 0) {
              throw  new BizException(PAGEELEMENT_EXIST_ERROR);
        }

        return pageElementService.save(pageElement);
    }

    @PostMapping ("/edit")
    @ApiOperation(value = "编辑")
    @Log(description = "编辑")
    public boolean editPageElement(@RequestBody Map<String, TPageElement> param) {
        TPageElement pageElement = param.get("params");
        if (StringUtils.isEmpty(pageElement.getElementName()) || StringUtils.isEmpty(pageElement.getByType()) || StringUtils.isEmpty(pageElement.getByValue())) {
            throw new EntityNotFoundException(TPageElement.class,"ElementName",pageElement.getElementName());
        }
        List<TPageElement> pageElements = pageElementService.lambdaQuery().eq(TPageElement::getElementName,pageElement.getElementName()).
                eq(TPageElement::getPageId,pageElement.getPageId()).
                eq(TPageElement::getId,pageElement.getId()).list( );
        if (pageElements.size() > 0) {
            throw  new BizException(PAGEELEMENT_EXIST_ERROR);
        }
        return pageElementService.updateElement(pageElement);
    }

    @GetMapping ("/remove/{id}")
    @ApiOperation(value = "删除")
    @Log(description = "删除")
    public boolean delPageElement(@PathVariable Integer id) {
        return pageElementService.removeById(id);
    }

    @PostMapping("/batchMoveElements")
    @ApiOperation(value = "批量删除")
    @Log(description = "批量删除")
    public boolean batchDeleteElements(@RequestBody Map<String,List<EditElementVo>> param) {
        List<EditElementVo> editElementVos = param.get("params");
        return pageElementService.batchRemoveById(editElementVos);
    }


    @GetMapping("/copyElemenById/{id}")
    @ApiOperation(value = "通过id复制元素")
    @Log(description = "通过id复制元素")
    public boolean copyElemenById(@PathVariable Integer id) {

        return pageElementService.copyElemenById(id);
    }

    @PostMapping("/batchSaveElements")
    @ApiOperation(value = "批量保存")
    @Log(description = "批量保存")
    public boolean batchSaveElements(@RequestBody Map<String,List<EditElementVo>> param) {

        List<EditElementVo> editElementVos = param.get("params");
        return pageElementService.saveOrUpdateBatchVO(editElementVos);
    }

}
