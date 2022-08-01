package com.snail.abell.jwtLogin.controller;


import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.jwtLogin.security.entity.SelfUserEntity;
import com.snail.abell.permission.entity.SysMenu;
import com.snail.abell.permission.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 普通用户
 * @Author Abell
 * @CreateTime 2022/07/15
 */
@ResponseResult
@Api(tags = "用户登录")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 用户端信息
     * @Return Map<String,Object> 返回数据MAP
     */
    @ApiOperation(value = "用户Abell")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Map<String,Object> userLogin(){
        Map<String,Object> result = new HashMap<>();
        SelfUserEntity userDetails = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        result.put("title","用户端信息");
        result.put("data",userDetails);
        return result;
    }

    /**
     * 拥有USER角色和sys:user:info权限可以访问

     * @Return Map<String,Object> 返回数据MAP
     */
    @ApiOperation(value = "用户菜单列表")
    @PreAuthorize("hasRole('USER') and hasPermission('/user/menuList','sys:user:info')")
    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public Map<String,Object> sysMenuEntity(){
        Map<String,Object> result = new HashMap<>();
        List<SysMenu> sysMenuEntityList = sysMenuService.list();
        result.put("title","拥有USER角色和sys:user:info权限可以访问");
        result.put("data",sysMenuEntityList);
        return result;
    }

}
