package com.snail.abell.permission.vo;

import com.snail.abell.base.BaseMapper;
import com.snail.abell.permission.dto.UserDto;
import com.snail.abell.permission.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, SysUser> {
}
