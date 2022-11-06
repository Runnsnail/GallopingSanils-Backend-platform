package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.dto.TestCasesDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Abell
 * @date 2022/10/23
 */

@Repository
public interface TestCaseUiMapper extends BaseMapper<TestCasesDto> {

    String wrapperSql =
            "SELECT cu.id,cu.`name`,cu.`case_id`,cu.`status`,cu.create_by author,te.`name` envName,pr.`name` projectName,pr.card_title teamName FROM `t_testcase_ui_new` as cu " +
            "LEFT JOIN t_env as te ON te.id = cu.env_id " +
            "LEFT JOIN project as pr ON pr.id = cu.project_id " +
            "LEFT JOIN sys_user su ON su.username = cu.create_by " +
            "${ew.customSqlSegment}";

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select(wrapperSql)
    Page<TestCasesDto> selectPagesList(Page<TestCasesDto> page, @Param("ew") Wrapper<TestCasesDto> queryWrapper );




}
