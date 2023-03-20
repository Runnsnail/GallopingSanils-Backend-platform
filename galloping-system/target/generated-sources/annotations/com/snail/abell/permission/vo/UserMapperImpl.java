package com.snail.abell.permission.vo;

import com.snail.abell.permission.dto.UserDto;
import com.snail.abell.permission.entity.SysUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T10:26:17+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public SysUser toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setUsername( dto.getUsername() );
        sysUser.setNickName( dto.getNickName() );
        sysUser.setMember( dto.getMember() );
        sysUser.setPhone( dto.getPhone() );
        sysUser.setEmail( dto.getEmail() );
        sysUser.setAvatarName( dto.getAvatarName() );
        sysUser.setAvatarPath( dto.getAvatarPath() );
        sysUser.setPassword( dto.getPassword() );
        sysUser.setIsAdmin( dto.getIsAdmin() );
        sysUser.setEnabled( dto.getEnabled() );
        sysUser.setCreateBy( dto.getCreateBy() );
        sysUser.setUpdateBy( dto.getUpdateBy() );
        sysUser.setPwdResetTime( dto.getPwdResetTime() );
        sysUser.setCreateTime( dto.getCreateTime() );
        sysUser.setUpdateTime( dto.getUpdateTime() );

        return sysUser;
    }

    @Override
    public UserDto toDto(SysUser entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setCreateBy( entity.getCreateBy() );
        userDto.setUpdateBy( entity.getUpdateBy() );
        userDto.setCreateTime( entity.getCreateTime() );
        userDto.setUpdateTime( entity.getUpdateTime() );
        userDto.setUsername( entity.getUsername() );
        userDto.setNickName( entity.getNickName() );
        userDto.setEmail( entity.getEmail() );
        userDto.setPhone( entity.getPhone() );
        userDto.setMember( entity.getMember() );
        userDto.setAvatarName( entity.getAvatarName() );
        userDto.setAvatarPath( entity.getAvatarPath() );
        userDto.setPassword( entity.getPassword() );
        userDto.setEnabled( entity.getEnabled() );
        userDto.setIsAdmin( entity.getIsAdmin() );
        userDto.setPwdResetTime( entity.getPwdResetTime() );

        return userDto;
    }

    @Override
    public List<SysUser> toEntity(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysUser> list = new ArrayList<SysUser>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<SysUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( SysUser sysUser : entityList ) {
            list.add( toDto( sysUser ) );
        }

        return list;
    }
}
