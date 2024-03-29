package com.snail.abell.jwtLogin.security.handler;

import com.snail.abell.jwtLogin.common.config.JWTConfig;
import com.snail.abell.jwtLogin.common.util.JWTTokenUtil;
import com.snail.abell.jwtLogin.common.util.ResultUtil;
import com.snail.abell.jwtLogin.security.entity.SelfUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 * @Author Abell
 * @CreateTime 2022/7/17
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 登录成功返回结果
     * @Author Abell
     * @CreateTime 2022/07/15
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){

        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(selfUserEntity);
        token = JWTConfig.tokenPrefix + token;
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        Map<String,Object> date = new HashMap<>();
        date.put("userInfo",selfUserEntity);
        resultData.put("code",200);
        resultData.put("msg","登录成功");
        resultData.put("accessToken",token);
        resultData.put("data",date);
        ResultUtil.responseJson(response,resultData);
    }
}
