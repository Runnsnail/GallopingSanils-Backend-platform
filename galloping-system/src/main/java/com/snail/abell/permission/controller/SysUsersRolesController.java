package com.snail.abell.permission.controller;

import com.snail.abell.permission.entity.SysUsersRoles;
import com.snail.abell.permission.service.SysUsersRolesService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户角色关联(SysUsersRoles)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "用户角色关联")
@Validated
@RestController
@RequestMapping("/sysUsersRoles")
public class SysUsersRolesController {
    /**
     * 服务对象
     */
    @Resource
    private SysUsersRolesService sysUsersRolesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUsersRoles selectOne(Long id) {
        return this.sysUsersRolesService.queryById(id);
    }

}