package com.snail.abell.dto;

import com.snail.abell.entity.TStepUiNew;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Abell
 * @date 2022/11/25
 */

@Mapper
public interface StepMapper{

    StepMapper INSTANCE = Mappers.getMapper(StepMapper.class);

    StepMessageDto toStepMessageDto (TStepUiNew stepUiNew);
}
