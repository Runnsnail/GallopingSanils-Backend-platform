package com.snail.abell.jwtLogin.security.service;


import com.snail.abell.jwtLogin.security.entity.SelfUserEntity;
import com.snail.abell.permission.entity.SysUser;
import com.snail.abell.permission.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.regex.*;

/**
 * SpringSecurity用户的业务实现
 * @Author Abell
 * @CreateTime 2022/7/17
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户信息
     * @Author Abell
     * @CreateTime 2022/7/17 17:23
     * @Param  username  用户名
     * @Return UserDetails SpringSecurity用户信息
     */
    @Override
    public SelfUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        String regex = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        SysUser sysUser;
        if (match(regex,username)) {
            // 查询用户信息
             sysUser =sysUserService.selectUserByEmail(username);
        }else {
             sysUser =sysUserService.selectUserByName(username);
        }

        if (sysUser!=null){
            // 组装参数
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(sysUser,selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }


    private static boolean match( String regex ,String str ){

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher( str );

        return matcher.matches();

    }

}
