package com.snail.abell.permission.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.logInterface.Log;
import com.snail.abell.permission.entity.SysUser;
import com.snail.abell.permission.service.SysUserService;
import com.snail.abell.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户(SysUser)表控制层
 *
 * @author Abell
 * @since 2022-06-05 11:51:28
 */
@Api(tags = "系统用户")
@Validated
@ResponseResult
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public SysUser selectOne(Long id) {
        return this.sysUserService.queryById(id);
    }

    @GetMapping("/getMembers")
    @ApiOperation("查询Team所有成员")
    @Log(description = "查询Team所有成员")
    public ArrayList<HashMap<String, String>> getMembers() {
        ArrayList<HashMap<String, String>> members = this.sysUserService.getMembers();
        return members;
    }

    @PostMapping("/insertTeam")
    @ApiOperation("添加团队成员")
    @Log(description = "添加团队成员")
    public boolean addTeam( @RequestBody Map<String,String> memberData){

        String username = SecurityUtils.getCurrentUsername();
        return this.sysUserService.addTeam(memberData.get("memberCode"),username);
    }
}
