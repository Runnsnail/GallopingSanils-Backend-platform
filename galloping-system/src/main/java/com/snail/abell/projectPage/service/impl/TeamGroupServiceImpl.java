package com.snail.abell.projectPage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.permission.service.SysUserService;
import com.snail.abell.projectPage.dao.TeamGroupMapper;
import com.snail.abell.projectPage.entity.TeamGroup;
import com.snail.abell.projectPage.service.TeamGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Abell
 * @date  2022/9/8
 */
@Service
public class TeamGroupServiceImpl extends ServiceImpl<TeamGroupMapper, TeamGroup> implements TeamGroupService{

    @Resource
    private SysUserService sysUserService;

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
        sysUserService.updateTeams( memberList, memberCode);

    }
}
