package com.snail.abell.webUI.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.exception.EntityNotFoundException;
import com.snail.abell.webUI.entity.TPageElement;
import com.snail.abell.webUI.service.TPageElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/tPageElement")
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
    public TPageElement selectOne(Long id) {
        return this.pageElementService.queryById(id);
    }


    /**
     *
     * @param pageNum
     * @param pageSum
     * @param pageElement
     * @return 分页数据
     */
    @GetMapping("/listPageElement")
    @ApiOperation(value = "获取分页带参列表")
    public List<TPageElement> getListPageElement(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSum, TPageElement pageElement){

        Page<TPageElement> page = new Page<>(pageNum, pageSum);
        List<TPageElement> pageElementList = pageElementService.pageQuery(page, pageElement);
        return pageElementList;
    }


    @GetMapping("/listByElementId/{id}")
    @ApiOperation(value = "通过项目id取页面")
    public List<TPageElement> getlistProjectId(@PathVariable Long id) {
        List<TPageElement> projectPages = pageElementService.lambdaQuery().eq(TPageElement::getId,id).list();
        return projectPages;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
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

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public boolean editPageElement(@RequestBody TPageElement pageElement) {
        if (StringUtils.isEmpty(pageElement.getElementName()) || StringUtils.isEmpty(pageElement.getByType()) || StringUtils.isEmpty(pageElement.getByValue())) {
            throw new EntityNotFoundException(TPageElement.class,"ElementName",pageElement.getElementName());
        }
        List<TPageElement> pageElements = pageElementService.lambdaQuery().eq(TPageElement::getElementName,pageElement.getElementName()).
                eq(TPageElement::getPageId,pageElement.getPageId()).
                eq(TPageElement::getId,pageElement.getId()).list( );
        if (pageElements.size() > 0) {
            throw  new BizException(PAGEELEMENT_EXIST_ERROR);
        }
        return pageElementService.updateById(pageElement);
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public boolean delPageElement(@RequestBody TPageElement pageElement) {
        return pageElementService.removeById(pageElement.getId());
    }

    @GetMapping("/copyElemenById/{id}")
    @ApiOperation(value = "通过id复制元素")
    public boolean copyElemenById(@PathVariable Long id) {
        pageElementService.copyElemenById(id);
        return pageElementService.copyElemenById(id);
    }

    @PostMapping("/batchSaveElements")
    @ApiOperation(value = "批量保存")
    public boolean batchSaveElements(@RequestBody List<TPageElement> pageElements) {

        return pageElementService.saveOrUpdateBatch(pageElements);
    }

}