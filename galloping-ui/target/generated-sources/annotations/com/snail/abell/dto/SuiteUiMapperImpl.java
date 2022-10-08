package com.snail.abell.dto;

import com.snail.abell.entity.TTestsuiteUi;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-04T18:23:37+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class SuiteUiMapperImpl implements SuiteUiMapper {

    @Override
    public TTestsuiteUi toEntity(SuiteUiDto dto) {
        if ( dto == null ) {
            return null;
        }

        TTestsuiteUi tTestsuiteUi = new TTestsuiteUi();

        tTestsuiteUi.setId( dto.getId() );
        tTestsuiteUi.setName( dto.getName() );
        tTestsuiteUi.setProjectId( dto.getProjectId() );
        tTestsuiteUi.setCreateBy( dto.getCreateBy() );
        tTestsuiteUi.setUpdateBy( dto.getUpdateBy() );
        tTestsuiteUi.setCreateTime( dto.getCreateTime() );
        tTestsuiteUi.setUpdateTime( dto.getUpdateTime() );

        return tTestsuiteUi;
    }

    @Override
    public SuiteUiDto toDto(TTestsuiteUi entity) {
        if ( entity == null ) {
            return null;
        }

        SuiteUiDto suiteUiDto = new SuiteUiDto();

        suiteUiDto.setCreateBy( entity.getCreateBy() );
        suiteUiDto.setUpdateBy( entity.getUpdateBy() );
        suiteUiDto.setCreateTime( entity.getCreateTime() );
        suiteUiDto.setUpdateTime( entity.getUpdateTime() );
        suiteUiDto.setId( entity.getId() );
        suiteUiDto.setName( entity.getName() );
        suiteUiDto.setProjectId( entity.getProjectId() );

        return suiteUiDto;
    }

    @Override
    public List<TTestsuiteUi> toEntity(List<SuiteUiDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TTestsuiteUi> list = new ArrayList<TTestsuiteUi>( dtoList.size() );
        for ( SuiteUiDto suiteUiDto : dtoList ) {
            list.add( toEntity( suiteUiDto ) );
        }

        return list;
    }

    @Override
    public List<SuiteUiDto> toDto(List<TTestsuiteUi> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SuiteUiDto> list = new ArrayList<SuiteUiDto>( entityList.size() );
        for ( TTestsuiteUi tTestsuiteUi : entityList ) {
            list.add( toDto( tTestsuiteUi ) );
        }

        return list;
    }
}
