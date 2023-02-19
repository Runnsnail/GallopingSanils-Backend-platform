package com.snail.abell.dto;

import com.snail.abell.entity.TStepUiNew;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-12T18:14:32+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
public class StepMapperImpl implements StepMapper {

    @Override
    public StepMessageDto toStepMessageDto(TStepUiNew stepUiNew) {
        if ( stepUiNew == null ) {
            return null;
        }

        StepMessageDto stepMessageDto = new StepMessageDto();

        stepMessageDto.setId( stepUiNew.getId() );
        stepMessageDto.setName( stepUiNew.getName() );
        stepMessageDto.setActionType( stepUiNew.getActionType() );
        stepMessageDto.setAction( stepUiNew.getAction() );
        stepMessageDto.setTestcaseId( stepUiNew.getTestcaseId() );
        stepMessageDto.setPageId( stepUiNew.getPageId() );
        stepMessageDto.setElementId( stepUiNew.getElementId() );
        stepMessageDto.setType( stepUiNew.getType() );
        stepMessageDto.setCreateBy( stepUiNew.getCreateBy() );
        stepMessageDto.setCreateTime( stepUiNew.getCreateTime() );
        stepMessageDto.setUpdateBy( stepUiNew.getUpdateBy() );
        stepMessageDto.setUpdateTime( stepUiNew.getUpdateTime() );
        stepMessageDto.setSort( stepUiNew.getSort() );
        stepMessageDto.setIcon( stepUiNew.getIcon() );
        stepMessageDto.setVariant( stepUiNew.getVariant() );
        stepMessageDto.setEnable( stepUiNew.isEnable() );
        stepMessageDto.setRemark( stepUiNew.getRemark() );
        stepMessageDto.setOptionData( stepUiNew.getOptionData() );
        stepMessageDto.setWaite( stepUiNew.getWaite() );
        stepMessageDto.setCounts( stepUiNew.getCounts() );

        return stepMessageDto;
    }
}
