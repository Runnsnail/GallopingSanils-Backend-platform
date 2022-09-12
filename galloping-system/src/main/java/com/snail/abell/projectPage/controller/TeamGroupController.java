package com.snail.abell.projectPage.controller;

import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.exception.BizException;
import com.snail.abell.logInterface.Log;
import com.snail.abell.projectPage.entity.TeamGroup;
import com.snail.abell.projectPage.entity.TeamGroupMapper;
import com.snail.abell.projectPage.entity.TeamGroups;
import com.snail.abell.projectPage.service.TeamGroupService;
import com.snail.abell.utils.DateUtil;
import com.snail.abell.utils.SerialUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

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
    public Map<String, List<TeamGroup>> getTeamGroups(){

        List<TeamGroup> teamGroups = teamGroupService.list();
        Map<String, List<TeamGroup>> map = new HashMap<>();
        map.put("teamData",teamGroups);
        return map;
    }

    @PostMapping("/addTeam")
    @ApiOperation(value = "新增团队")
    @Log(description = "新增团队")
    public boolean savaProjectPage(@RequestBody TeamGroups teamGroup) {

        List<TeamGroup> teamGroups = teamGroupService.lambdaQuery().eq(TeamGroup::getTeamName,teamGroup.getTeamName()).list();
        if (teamGroups.size() > 0) {
            throw new BizException(TEAM_EXIST_ERROR);
        }
        String memberCode = SerialUtil.nextValue();
        TeamGroup teams = TeamGroupMapper.INSTANCE.DTO(teamGroup);
        teams.setCreateTime(DateUtil.toDate(LocalDateTime.now()));
        teams.setTeamMember(memberCode);
        //更新员工MemberCode memberName
        ArrayList<HashMap<String, String>> memberList = teamGroup.getTeamMember();
        teamGroupService.updateTeams(memberList,memberCode);
        return teamGroupService.save(teams);
    }

}
