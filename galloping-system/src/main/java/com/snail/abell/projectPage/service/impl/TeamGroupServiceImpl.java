package com.snail.abell.projectPage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.exception.BizException;
import com.snail.abell.permission.dao.SysUserDao;
import com.snail.abell.permission.entity.SysUser;
import com.snail.abell.permission.service.SysUserService;
import com.snail.abell.projectPage.dao.TeamGroupMapper;
import com.snail.abell.projectPage.entity.TeamGroup;
import com.snail.abell.projectPage.service.TeamGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.snail.abell.base.ResultCode.TEAM_NOT_EXIST_ERROR;

/**
 * @author Abell
 * @date 2022/9/8
 */
@Service
public class TeamGroupServiceImpl extends ServiceImpl<TeamGroupMapper, TeamGroup> implements TeamGroupService {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private TeamGroupService teamGroupService;

    @Override
    public int updateBatch(List<TeamGroup> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<TeamGroup> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<TeamGroup> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(TeamGroup record) {
        return baseMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(TeamGroup record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public void updateTeams(ArrayList<HashMap<String, String>> memberList, String memberCode) {
        sysUserService.updateTeams(memberList, memberCode);

    }

    @Override
    public boolean removeTeam(Integer id) {
        TeamGroup team = teamGroupService.getById(id);
        if (team == null) {
            throw new BizException(TEAM_NOT_EXIST_ERROR);
        }
        List<SysUser> memberUserList = sysUserService.lambdaQuery().eq(SysUser::getMember, team.getTeamMember()).list();
        List<String> memberNameList = memberUserList.stream().map(SysUser::getMember).collect(Collectors.toList());
        sysUserDao.updateTeams(memberNameList, null);
        return teamGroupService.removeById(id);
    }
}
