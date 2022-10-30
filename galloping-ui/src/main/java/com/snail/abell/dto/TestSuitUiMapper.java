package com.snail.abell.dto;

import com.snail.abell.Vo.TestSuitUiVo;
import com.snail.abell.base.BaseMapper;
import com.snail.abell.entity.TTestsuiteUi;
import org.mapstruct.*;

/**
 * @author Abell
 * @date 2022/10/12
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestSuitUiMapper extends BaseMapper<TestSuitUiVo, TTestsuiteUi> {

    @Mappings({
            @Mapping(source = "name", target = "label"),
            @Mapping(target = "createBy", ignore = true),
            @Mapping(target = "updateBy", ignore = true),
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "updateTime", ignore = true),
    })
    @Override
    @Named("toDto")
    TestSuitUiVo toDto(TTestsuiteUi testSuitUi);
}
