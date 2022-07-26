package com.snail.abell.jwtLogin.controller;

import com.snail.abell.jwtLogin.common.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始页面
 * @Author Abell
 * @CreateTime 2022/7/17  15:11
 */
@Controller
@RequestMapping("/login")
public class IndexController {


    /**
     * 首页
     * @Author Abell
     * @CreateTime 2022/07/15
     * @Return Map<String,Object> 返回数据MAP
     */
    @RequestMapping(value = "/loginss",method = RequestMethod.GET)
    public Map<String,Object> userLogin(){
        // 组装参数
        Map<String,Object> result = new HashMap<>();
        result.put("title","这里是首页不需要权限和登录拦截");
        return ResultUtil.resultSuccess(result);
    }

//    @RequestMapping(value = "/userlogin",method = RequestMethod.POST)
//    public String index(Model model){
//        System.out.println("xxxxxxxxxxx");
//        return "html-customizer/ltr/vertical-menu-template-dark/dashboard-ecommerce";
//    }

    @RequestMapping("/home")
    public String home( Model model){

        return "html-customizer/ltr/vertical-menu-template-dark/dashboard-ecommerce";
    }

    @RequestMapping("/userLogout")
    public String loginOut(){
        return "/html-customizer/ltr/vertical-menu-template-dark/auth-register-basic";
    }
}
