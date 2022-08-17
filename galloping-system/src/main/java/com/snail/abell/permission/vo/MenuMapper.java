package com.snail.abell.permission.vo;

import com.snail.abell.base.BaseMapper;
import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.entity.SysMenu;
import org.mapstruct.*;

/**
 * @author Abell
 * @date 2020/6/6
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, SysMenu> {

    @Mappings({@Mapping(source = "name", target = "componentName")})
    @Override
    @Named("toDto")
    MenuDto toDto(SysMenu menu);
}
