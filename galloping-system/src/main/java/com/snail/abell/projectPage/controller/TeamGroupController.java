package com.snail.abell.projectPage.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.projectPage.entity.TeamGroup;
import com.snail.abell.projectPage.service.TeamGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

import static com.snail.abell.base.ResultCode.TEAM_EXIST_ERROR;

/**
 * @author Abell
 * @date 2022/9/8
 */

@Api(tags = "团队管理")
@Validated
@RestController
@ResponseResult
@RequestMapping("/teamGroup")
public class TeamGroupController {

    @Resource
    private TeamGroupService teamGroupService;

    @GetMapping("getTeams")
    @ApiOperation("查询所有团队信息")
    @Log(description = "查询所有团队信息")
    public List<TeamGroup> getTeamGroups(){

        List<TeamGroup> teamGroups = teamGroupService.list();
        return teamGroups;
    }

    @PostMapping("/addMember")
    @ApiOperation(value = "新增团队")
    @Log(description = "新增团队")
    public boolean savaProjectPage(@RequestBody TeamGroup teamGroup) {

        List<TeamGroup> teamGroups = teamGroupService.lambdaQuery().eq(TeamGroup::getTeamName,teamGroup.getTeamName()).list();
        if (teamGroups.size() > 0) {
            throw new BizException(TEAM_EXIST_ERROR);
        }
        teamGroup.setCreateTime(Calendar.getInstance().getTime());
        return teamGroupService.save(teamGroup);
    }

}
