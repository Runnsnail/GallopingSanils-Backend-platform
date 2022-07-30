package com.snail.abell.permission.vo;

import com.snail.abell.base.BaseMapper;
import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.entity.SysMenu;
import org.mapstruct.Mapper;

/**
 * @author Abell
 * @date 2020/6/6
 */
@Mapper(componentModel = "spring")
public interface MenuMapper extends BaseMapper<MenuDto, SysMenu> {


}
