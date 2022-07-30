package com.snail.abell.permission.vo;

import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.entity.SysMenu;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T20:11:37+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public SysMenu toEntity(MenuDto dto) {
        if ( dto == null ) {
            return null;
        }

        SysMenu sysMenu = new SysMenu();

        sysMenu.setMenuId( dto.getMenuId() );
        sysMenu.setPid( dto.getPid() );
        sysMenu.setSubCount( dto.getSubCount() );
        sysMenu.setType( dto.getType() );
        sysMenu.setTitle( dto.getTitle() );
        sysMenu.setComponent( dto.getComponent() );
        sysMenu.setMenuSort( dto.getMenuSort() );
        sysMenu.setIcon( dto.getIcon() );
        sysMenu.setPath( dto.getPath() );
        sysMenu.setIframe( dto.getIframe() );
        sysMenu.setCache( dto.getCache() );
        sysMenu.setHidden( dto.getHidden() );
        sysMenu.setPermission( dto.getPermission() );
        sysMenu.setCreateBy( dto.getCreateBy() );
        sysMenu.setUpdateBy( dto.getUpdateBy() );
        sysMenu.setCreateTime( dto.getCreateTime() );
        sysMenu.setUpdateTime( dto.getUpdateTime() );

        return sysMenu;
    }

    @Override
    public MenuDto toDto(SysMenu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setCreateBy( entity.getCreateBy() );
        menuDto.setUpdateBy( entity.getUpdateBy() );
        if ( entity.getCreateTime() != null ) {
            menuDto.setCreateTime( new Timestamp( entity.getCreateTime().getTime() ) );
        }
        if ( entity.getUpdateTime() != null ) {
            menuDto.setUpdateTime( new Timestamp( entity.getUpdateTime().getTime() ) );
        }
        menuDto.setMenuId( entity.getMenuId() );
        menuDto.setType( entity.getType() );
        menuDto.setPermission( entity.getPermission() );
        menuDto.setTitle( entity.getTitle() );
        menuDto.setMenuSort( entity.getMenuSort() );
        menuDto.setPath( entity.getPath() );
        menuDto.setComponent( entity.getComponent() );
        menuDto.setPid( entity.getPid() );
        menuDto.setSubCount( entity.getSubCount() );
        menuDto.setIframe( entity.getIframe() );
        menuDto.setCache( entity.getCache() );
        menuDto.setHidden( entity.getHidden() );
        menuDto.setIcon( entity.getIcon() );

        return menuDto;
    }

    @Override
    public List<SysMenu> toEntity(List<MenuDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysMenu> list = new ArrayList<SysMenu>( dtoList.size() );
        for ( MenuDto menuDto : dtoList ) {
            list.add( toEntity( menuDto ) );
        }

        return list;
    }

    @Override
    public List<MenuDto> toDto(List<SysMenu> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( entityList.size() );
        for ( SysMenu sysMenu : entityList ) {
            list.add( toDto( sysMenu ) );
        }

        return list;
    }
}
