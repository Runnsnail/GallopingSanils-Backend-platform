package com.snail.abell.permission.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.permission.dao.SysDictDetailDao;
import com.snail.abell.permission.entity.SysDictDetail;
import com.snail.abell.permission.service.SysDictDetailService;
import com.snail.abell.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.snail.abell.base.ResultCode.NOT_FOUND;

/**
 * 数据字典详情(SysDictDetail)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "数据字典详情")
@Validated
@ResponseResult
@RestController
@RequestMapping("/api/dictDetail")
public class SysDictDetailController {
    /**
     * 服务对象
     */
    @Resource
    private SysDictDetailService sysDictDetailService;
    @Autowired
    private SysDictDetailDao sysDictDetailDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/listDictValue/{id}")
    public List<SysDictDetail> getPageList(@PathVariable Long id) {

        return sysDictDetailService.lambdaQuery().eq(SysDictDetail::getDictId, id).list();
    }

    @PostMapping("/addDictValue")
    public Boolean savaDict(@RequestBody SysDictDetail sysDict) {
        List<SysDictDetail> SysDictDetailList = sysDictDetailService.lambdaQuery().eq(SysDictDetail::getDictId, sysDict.getDictId()).list();
        if (SysDictDetailList.size() > 0) {
            throw new BizException(NOT_FOUND);
        }
        sysDict.setCreateBy(SecurityUtils.getCurrentUsername());
        sysDictDetailService.save(sysDict);
        return sysDictDetailService.save(sysDict);
    }

    @PutMapping("/editDictValue")
    public Boolean editDict(@RequestBody SysDictDetail sysDict) {
        List<SysDictDetail> SysDictDetailList = sysDictDetailService.lambdaQuery().eq(SysDictDetail::getDictId, sysDict.getDictId()).list();
        if (SysDictDetailList.size() > 0) {
            throw new BizException(NOT_FOUND);
        }
        sysDict.setCreateBy(SecurityUtils.getCurrentUsername());
        return sysDictDetailService.updateById(sysDict);
    }

    @PostMapping("/removeDictValue/{id}")
    public Boolean delDict(@PathVariable Long id) {

        return sysDictDetailService.removeById(id);
    }

    @PostMapping("/getDictValueById/{id}")
    public SysDictDetail getDict(@PathVariable Long id) {
        return sysDictDetailService.getById(id);
    }

    @GetMapping("/dictValue/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public List<SysDictDetail> getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        Page<SysDictDetail> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysDictDetail> queryWrapper = new QueryWrapper<>();

        return this.sysDictDetailDao.selectPage(page, queryWrapper).getRecords();

    }
}