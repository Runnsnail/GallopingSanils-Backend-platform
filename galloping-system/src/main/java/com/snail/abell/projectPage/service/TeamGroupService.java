package com.snail.abell.projectPage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.projectPage.entity.TeamGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Abell
 * @date  2022/9/8
 */
public interface TeamGroupService extends IService<TeamGroup>{


    int updateBatch(List<TeamGroup> list);

    int updateBatchSelective(List<TeamGroup> list);

    int batchInsert(List<TeamGroup> list);

    int insertOrUpdate(TeamGroup record);

    int insertOrUpdateSelective(TeamGroup record);

    void updateTeams(ArrayList<HashMap<String, String>> memberList, String memberCode);
}
