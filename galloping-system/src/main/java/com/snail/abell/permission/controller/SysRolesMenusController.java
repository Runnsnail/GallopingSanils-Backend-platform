package com.snail.abell.permission.controller;

import com.snail.abell.permission.entity.SysRolesMenus;
import com.snail.abell.permission.service.SysRolesMenusService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色菜单关联(SysRolesMenus)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "角色菜单关联")
@Validated
@RestController
@RequestMapping("/sysRolesMenus")
public class SysRolesMenusController {
    /**
     * 服务对象
     */
    @Resource
    private SysRolesMenusService sysRolesMenusService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRolesMenus selectOne(Long id) {
        return this.sysRolesMenusService.queryById(id);
    }

}