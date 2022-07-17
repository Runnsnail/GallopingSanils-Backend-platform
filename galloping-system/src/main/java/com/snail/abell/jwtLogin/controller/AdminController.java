package com.snail.abell.jwtLogin.controller;


import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.entity.SysRole;
import com.snail.abell.permission.entity.SysUser;
import com.snail.abell.permission.service.SysMenuService;
import com.snail.abell.permission.service.SysRoleService;
import com.snail.abell.permission.service.SysUserService;
import com.snail.abell.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端
 * @Author Abell
 * @CreateTime 2022/07/16 14:16
 */
@Api(tags = "管理员接口")
@ResponseResult
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 管理端信息
     * @Return Map<String,Object> 返回数据MAP
     */
    @ApiOperation(value = "测试管理员可以访问")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Map<String,Object> userLogin(){
        Map<String,Object> result = new HashMap<>();
        UserDetails userDetails = SecurityUtils.getCurrentUser();
        result.put("title","管理端信息");
        result.put("data",userDetails);
        return result;
    }

    /**
     * 拥有ADMIN或者USER角色可以访问
     * @Return Map<String,Object> 返回数据MAP
     */
    @ApiOperation(value = "测试管理员ADMIN或者USER角色可以访问")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map<String,Object> list(){
        Map<String,Object> result = new HashMap<>();
        List<SysUser> sysUserEntityList = sysUserService.list();
        result.put("title","拥有用户或者管理员角色都可以查看");
        result.put("data",sysUserEntityList);
        return result;
    }

    /**
     * 拥有ADMIN和USER角色可以访问
     * @Return Map<String,Object> 返回数据MAP
     */
    @ApiOperation(value = "ADMIN和USER角色可以访问")
    @PreAuthorize("hasRole('ADMIN') and hasRole('USER')")
    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public Map<String,Object> menuList(){
        Map<String,Object> result = new HashMap<>();
        List<SysMenu> sysMenuEntityList = sysMenuService.list();
        result.put("title","拥有用户和管理员角色都可以查看");
        result.put("data",sysMenuEntityList);
        return result;
    }


    /**
     * 拥有sys:user:info权限可以访问
     * hasPermission 第一个参数是请求路径 第二个参数是权限表达式
     * @Return Map<String,Object> 返回数据MAP
     */
    @ApiOperation(value = "测试路径和按钮权限控制")
    @PreAuthorize("hasPermission('/admin/userList','sys:user:info')")
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public Map<String,Object> userList(){
        Map<String,Object> result = new HashMap<>();
        List<SysUser> sysUserEntityList = sysUserService.list();
        result.put("title","拥有sys:user:info权限都可以查看");
        result.put("data",sysUserEntityList);
        return result;
    }


    /**
     * 拥有ADMIN角色和sys:role:info权限可以访问
     * @Return Map<String,Object> 返回数据MAP
     */
    @ApiOperation(value = "")
    @PreAuthorize("hasRole('ADMIN') and hasPermission('/admin/adminRoleList','sys:role:info')")
    @RequestMapping(value = "/adminRoleList",method = RequestMethod.GET)
    public Map<String,Object> adminRoleList(){
        Map<String,Object> result = new HashMap<>();
        List<SysRole> sysRoleEntityList = sysRoleService.list();
        result.put("title","拥有ADMIN角色和sys:role:info权限可以访问");
        result.put("data",sysRoleEntityList);
        return result;
    }
}
