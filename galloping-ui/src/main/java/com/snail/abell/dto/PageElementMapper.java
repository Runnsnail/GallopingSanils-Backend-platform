package com.snail.abell.dto;

import com.snail.abell.Vo.PageElementVo;
import com.snail.abell.base.BaseMapper;
import com.snail.abell.entity.TPageElement;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

/**
 * @author Abell
 * @date 2022/10/4
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageElementMapper extends BaseMapper<PageElementVo, TPageElement> {

    @Named("toDto")
    TPageElement toDto(PageElementVo pageElementVo);

}
