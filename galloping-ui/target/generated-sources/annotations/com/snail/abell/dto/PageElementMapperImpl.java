package com.snail.abell.dto;

import com.snail.abell.Vo.PageElementVo;
import com.snail.abell.entity.TPageElement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T10:26:10+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class PageElementMapperImpl implements PageElementMapper {

    @Override
    public TPageElement toEntity(PageElementVo dto) {
        if ( dto == null ) {
            return null;
        }

        TPageElement tPageElement = new TPageElement();

        tPageElement.setId( dto.getId() );
        tPageElement.setPageId( dto.getPageId() );
        tPageElement.setElementName( dto.getElementName() );
        tPageElement.setByType( dto.getByType() );
        tPageElement.setByValue( dto.getByValue() );
        tPageElement.setIsEnable( dto.getIsEnable() );
        tPageElement.setRemark( dto.getRemark() );
        tPageElement.setCreateBy( dto.getCreateBy() );
        tPageElement.setCreateTime( dto.getCreateTime() );
        tPageElement.setUpdateBy( dto.getUpdateBy() );
        tPageElement.setUpdateTime( dto.getUpdateTime() );

        return tPageElement;
    }

    @Override
    public PageElementVo toDto(TPageElement entity) {
        if ( entity == null ) {
            return null;
        }

        PageElementVo pageElementVo = new PageElementVo();

        pageElementVo.setCreateBy( entity.getCreateBy() );
        pageElementVo.setUpdateBy( entity.getUpdateBy() );
        pageElementVo.setCreateTime( entity.getCreateTime() );
        pageElementVo.setUpdateTime( entity.getUpdateTime() );
        pageElementVo.setId( entity.getId() );
        pageElementVo.setPageId( entity.getPageId() );
        pageElementVo.setElementName( entity.getElementName() );
        pageElementVo.setByType( entity.getByType() );
        pageElementVo.setByValue( entity.getByValue() );
        pageElementVo.setIsEnable( entity.getIsEnable() );
        pageElementVo.setRemark( entity.getRemark() );

        return pageElementVo;
    }

    @Override
    public List<TPageElement> toEntity(List<PageElementVo> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TPageElement> list = new ArrayList<TPageElement>( dtoList.size() );
        for ( PageElementVo pageElementVo : dtoList ) {
            list.add( toEntity( pageElementVo ) );
        }

        return list;
    }

    @Override
    public List<PageElementVo> toDto(List<TPageElement> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PageElementVo> list = new ArrayList<PageElementVo>( entityList.size() );
        for ( TPageElement tPageElement : entityList ) {
            list.add( toDto( tPageElement ) );
        }

        return list;
    }

    @Override
    public TPageElement toDto(PageElementVo pageElementVo) {
        if ( pageElementVo == null ) {
            return null;
        }

        TPageElement tPageElement = new TPageElement();

        tPageElement.setId( pageElementVo.getId() );
        tPageElement.setPageId( pageElementVo.getPageId() );
        tPageElement.setElementName( pageElementVo.getElementName() );
        tPageElement.setByType( pageElementVo.getByType() );
        tPageElement.setByValue( pageElementVo.getByValue() );
        tPageElement.setIsEnable( pageElementVo.getIsEnable() );
        tPageElement.setRemark( pageElementVo.getRemark() );
        tPageElement.setCreateBy( pageElementVo.getCreateBy() );
        tPageElement.setCreateTime( pageElementVo.getCreateTime() );
        tPageElement.setUpdateBy( pageElementVo.getUpdateBy() );
        tPageElement.setUpdateTime( pageElementVo.getUpdateTime() );

        return tPageElement;
    }
}
