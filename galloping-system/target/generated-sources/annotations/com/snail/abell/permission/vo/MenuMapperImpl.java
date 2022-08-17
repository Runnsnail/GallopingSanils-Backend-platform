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
    date = "2022-08-14T12:57:10+0800",
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
            list.add( sysMenuToMenuDto( sysMenu ) );
        }

        return list;
    }

    @Override
    public MenuDto toDto(SysMenu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setComponentName( menu.getName() );
        menuDto.setCreateBy( menu.getCreateBy() );
        menuDto.setUpdateBy( menu.getUpdateBy() );
        if ( menu.getCreateTime() != null ) {
            menuDto.setCreateTime( new Timestamp( menu.getCreateTime().getTime() ) );
        }
        if ( menu.getUpdateTime() != null ) {
            menuDto.setUpdateTime( new Timestamp( menu.getUpdateTime().getTime() ) );
        }
        menuDto.setMenuId( menu.getMenuId() );
        menuDto.setType( menu.getType() );
        menuDto.setPermission( menu.getPermission() );
        menuDto.setTitle( menu.getTitle() );
        menuDto.setMenuSort( menu.getMenuSort() );
        menuDto.setPath( menu.getPath() );
        menuDto.setComponent( menu.getComponent() );
        menuDto.setPid( menu.getPid() );
        menuDto.setSubCount( menu.getSubCount() );
        menuDto.setIframe( menu.getIframe() );
        menuDto.setCache( menu.getCache() );
        menuDto.setHidden( menu.getHidden() );
        menuDto.setIcon( menu.getIcon() );

        return menuDto;
    }

    protected MenuDto sysMenuToMenuDto(SysMenu sysMenu) {
        if ( sysMenu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setCreateBy( sysMenu.getCreateBy() );
        menuDto.setUpdateBy( sysMenu.getUpdateBy() );
        if ( sysMenu.getCreateTime() != null ) {
            menuDto.setCreateTime( new Timestamp( sysMenu.getCreateTime().getTime() ) );
        }
        if ( sysMenu.getUpdateTime() != null ) {
            menuDto.setUpdateTime( new Timestamp( sysMenu.getUpdateTime().getTime() ) );
        }
        menuDto.setMenuId( sysMenu.getMenuId() );
        menuDto.setType( sysMenu.getType() );
        menuDto.setPermission( sysMenu.getPermission() );
        menuDto.setTitle( sysMenu.getTitle() );
        menuDto.setMenuSort( sysMenu.getMenuSort() );
        menuDto.setPath( sysMenu.getPath() );
        menuDto.setComponent( sysMenu.getComponent() );
        menuDto.setPid( sysMenu.getPid() );
        menuDto.setSubCount( sysMenu.getSubCount() );
        menuDto.setIframe( sysMenu.getIframe() );
        menuDto.setCache( sysMenu.getCache() );
        menuDto.setHidden( sysMenu.getHidden() );
        menuDto.setIcon( sysMenu.getIcon() );

        return menuDto;
    }
}
