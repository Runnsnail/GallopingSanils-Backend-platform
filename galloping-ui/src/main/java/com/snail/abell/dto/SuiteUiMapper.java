package com.snail.abell.dto;


import com.snail.abell.base.BaseMapper;
import com.snail.abell.entity.TTestsuiteUi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author huawei
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuiteUiMapper extends BaseMapper<SuiteUiDto, TTestsuiteUi> {
}
