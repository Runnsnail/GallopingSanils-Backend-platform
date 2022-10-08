package com.snail.abell.permission.vo;

import com.snail.abell.permission.dto.MenuDto;
import com.snail.abell.permission.entity.SysMenu;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-08T15:43:53+0800",
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
        sysMenu.setPageTitle( dto.getPageTitle() );
        sysMenu.setComponent( dto.getComponent() );
        sysMenu.setMenuSort( dto.getMenuSort() );
        sysMenu.setTextone( dto.getTextone() );
        sysMenu.setTexttwo( dto.getTexttwo() );
        sysMenu.setActive( dto.getActive() );
        sysMenu.setPath( dto.getPath() );
        sysMenu.setIframe( dto.getIframe() );
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
        menuDto.setCreateTime( menu.getCreateTime() );
        menuDto.setUpdateTime( menu.getUpdateTime() );
        menuDto.setMenuId( menu.getMenuId() );
        menuDto.setType( menu.getType() );
        menuDto.setPermission( menu.getPermission() );
        menuDto.setPageTitle( menu.getPageTitle() );
        menuDto.setMenuSort( menu.getMenuSort() );
        menuDto.setTextone( menu.getTextone() );
        menuDto.setTexttwo( menu.getTexttwo() );
        menuDto.setPath( menu.getPath() );
        menuDto.setComponent( menu.getComponent() );
        menuDto.setPid( menu.getPid() );
        menuDto.setSubCount( menu.getSubCount() );
        menuDto.setIframe( menu.getIframe() );
        menuDto.setActive( menu.getActive() );
        menuDto.setHidden( menu.getHidden() );

        return menuDto;
    }

    protected MenuDto sysMenuToMenuDto(SysMenu sysMenu) {
        if ( sysMenu == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setCreateBy( sysMenu.getCreateBy() );
        menuDto.setUpdateBy( sysMenu.getUpdateBy() );
        menuDto.setCreateTime( sysMenu.getCreateTime() );
        menuDto.setUpdateTime( sysMenu.getUpdateTime() );
        menuDto.setMenuId( sysMenu.getMenuId() );
        menuDto.setType( sysMenu.getType() );
        menuDto.setPermission( sysMenu.getPermission() );
        menuDto.setPageTitle( sysMenu.getPageTitle() );
        menuDto.setMenuSort( sysMenu.getMenuSort() );
        menuDto.setTextone( sysMenu.getTextone() );
        menuDto.setTexttwo( sysMenu.getTexttwo() );
        menuDto.setPath( sysMenu.getPath() );
        menuDto.setComponent( sysMenu.getComponent() );
        menuDto.setPid( sysMenu.getPid() );
        menuDto.setSubCount( sysMenu.getSubCount() );
        menuDto.setIframe( sysMenu.getIframe() );
        menuDto.setActive( sysMenu.getActive() );
        menuDto.setHidden( sysMenu.getHidden() );

        return menuDto;
    }
}
