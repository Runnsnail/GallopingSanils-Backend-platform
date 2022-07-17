package com.snail.abell.webUI.dto;


import com.snail.abell.base.BaseMapper;
import com.snail.abell.webUI.entity.TTestsuiteUi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuiteUiMapper extends BaseMapper<SuiteUiDto, TTestsuiteUi> {
}
