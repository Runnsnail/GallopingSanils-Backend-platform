package com.snail.abell.permission.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.permission.entity.SysDict;
import com.snail.abell.permission.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static com.snail.abell.base.ResultCode.DICT_NOT_EXIST_ERROR;

/**
 * 数据字典(SysDict)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "数据字典")
@Validated
@ResponseResult
@RestController
@RequestMapping("/sysDict")
public class SysDictController {
    /**
     * 服务对象
     */
    @Resource
    private SysDictService sysDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysDict selectOne(Long id) {
        return this.sysDictService.queryById(id);
    }


    @ApiOperation("查询字典")
    @GetMapping(value = "/all")
    @PreAuthorize("@el.check('dict:list')")
    public List<SysDict> queryAllDict(){
        return sysDictService.list();
    }



    @Log("新增字典")
    @ApiOperation("新增字典")
    @PostMapping
    @PreAuthorize("@el.check('dict:add')")
    public Boolean createDict(@Validated @RequestBody SysDict resources){
        if (resources.getDictId() != null) {
            throw new BizException(DICT_NOT_EXIST_ERROR);
        }
        return sysDictService.save(resources);
    }

    @Log("修改字典")
    @ApiOperation("修改字典")
    @PutMapping
    @PreAuthorize("@el.check('dict:edit')")
    public Boolean updateDict(@Validated @RequestBody SysDict resources){

        return sysDictService.updateById(resources);
    }

    @Log("删除字典")
    @ApiOperation("删除字典")
    @DeleteMapping
    @PreAuthorize("@el.check('dict:del')")
    public Boolean deleteDict(@RequestBody Set<Long> ids){

        return sysDictService.removeByIds(ids);
    }
}