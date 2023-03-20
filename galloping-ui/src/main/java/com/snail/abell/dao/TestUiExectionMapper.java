package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.dto.UiExectionDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Abell
 * @date 2023/3/11
 */
public interface TestUiExectionMapper  extends BaseMapper<UiExectionDto> {

    String wrapperSql =
            "SELECT eu.id,eu.`name`,eu.status,eu.create_by,eu.notification_type envId,te.`name` envName,ms.type notificationType FROM t_testsuite_ui as eu " +
                    "LEFT JOIN t_env as te ON eu.notification_type =  te.id  " +
                    "LEFT JOIN message_send as ms ON eu.id = ms.suite_id " +
                    "${ew.customSqlSegment}";


    @Select(wrapperSql)
    IPage<UiExectionDto> selectPagesList(Page<UiExectionDto> page, @Param("ew") QueryWrapper<UiExectionDto> queryWrapper);
}
