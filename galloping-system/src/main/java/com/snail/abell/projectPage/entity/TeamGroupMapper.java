package com.snail.abell.projectPage.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Abell
 * @date 2022/9/12
 */
@Mapper
public interface TeamGroupMapper {

    TeamGroupMapper INSTANCE = Mappers.getMapper(TeamGroupMapper.class);

    @Mapping(target = "teamMember",ignore = true)
    TeamGroup DTO(TeamGroups teamGroup);
}
