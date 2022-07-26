package com.snail.abell.permission.vo;

import com.snail.abell.base.BaseMapper;
import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.entity.SysMenu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Abell
 * @date 2020/6/6
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, SysMenu> {


}
