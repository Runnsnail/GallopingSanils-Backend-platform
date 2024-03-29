package com.snail.abell.jwtLogin.security.handler;

import com.snail.abell.jwtLogin.common.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 暂无权限处理类
 * @Author Abell
 * @CreateTime 2022/07/15
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler{
    /**
     * 暂无权限返回结果
     * @Author Abell
     * @CreateTime 2019/10/3 8:41
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(403,"未授权"));
    }
}
