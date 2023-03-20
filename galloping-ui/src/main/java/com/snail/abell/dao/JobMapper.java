package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.JobAndTrigger;

import java.util.List;

/**
 * @author Abell
 * @date 2023/3/13
 */
public interface JobMapper extends BaseMapper<JobAndTrigger> {


    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list();
}
