package com.snail.abell.dto;

import com.snail.abell.Vo.EditElementVo;
import com.snail.abell.entity.TPageElement;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Abell
 * @date 2022/10/6
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EditElementMapper  {

    EditElementMapper INSTANCT = Mappers.getMapper(EditElementMapper.class);
    TPageElement toPageElement(EditElementVo editElementVo);

    List<TPageElement> toPageElementsList(List<EditElementVo> editElementVoList);


    @Named("toDto")
    TPageElement toDto(EditElementVo editElementVo);
}
