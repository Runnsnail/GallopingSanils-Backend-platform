package com.snail.abell.dto;

import com.snail.abell.Vo.EditElementVo;
import com.snail.abell.entity.TPageElement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-12T20:35:22+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class EditElementMapperImpl implements EditElementMapper {

    @Override
    public TPageElement toPageElement(EditElementVo editElementVo) {
        if ( editElementVo == null ) {
            return null;
        }

        TPageElement tPageElement = new TPageElement();

        tPageElement.setId( editElementVo.getId() );
        tPageElement.setPageId( editElementVo.getPageId() );
        tPageElement.setElementName( editElementVo.getElementName() );
        tPageElement.setByType( editElementVo.getByType() );
        tPageElement.setByValue( editElementVo.getByValue() );
        tPageElement.setIsEnable( editElementVo.getIsEnable() );
        tPageElement.setRemark( editElementVo.getRemark() );
        tPageElement.setCreateBy( editElementVo.getCreateBy() );
        tPageElement.setCreateTime( editElementVo.getCreateTime() );
        tPageElement.setUpdateBy( editElementVo.getUpdateBy() );
        tPageElement.setUpdateTime( editElementVo.getUpdateTime() );

        return tPageElement;
    }

    @Override
    public List<TPageElement> toPageElementsList(List<EditElementVo> editElementVoList) {
        if ( editElementVoList == null ) {
            return null;
        }

        List<TPageElement> list = new ArrayList<TPageElement>( editElementVoList.size() );
        for ( EditElementVo editElementVo : editElementVoList ) {
            list.add( toPageElement( editElementVo ) );
        }

        return list;
    }

    @Override
    public TPageElement toDto(EditElementVo editElementVo) {
        if ( editElementVo == null ) {
            return null;
        }

        TPageElement tPageElement = new TPageElement();

        tPageElement.setId( editElementVo.getId() );
        tPageElement.setPageId( editElementVo.getPageId() );
        tPageElement.setElementName( editElementVo.getElementName() );
        tPageElement.setByType( editElementVo.getByType() );
        tPageElement.setByValue( editElementVo.getByValue() );
        tPageElement.setIsEnable( editElementVo.getIsEnable() );
        tPageElement.setRemark( editElementVo.getRemark() );
        tPageElement.setCreateBy( editElementVo.getCreateBy() );
        tPageElement.setCreateTime( editElementVo.getCreateTime() );
        tPageElement.setUpdateBy( editElementVo.getUpdateBy() );
        tPageElement.setUpdateTime( editElementVo.getUpdateTime() );

        return tPageElement;
    }
}
