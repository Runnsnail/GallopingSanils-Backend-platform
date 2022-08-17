/*
 *  Copyright
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.snail.abell.aspect;

import com.alibaba.fastjson.JSONObject;
import com.snail.abell.generator.pojo.SysLog;
import com.snail.abell.generator.service.SysLogService;
import com.snail.abell.logInterface.Log;
import com.snail.abell.utils.RequestHolder;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.utils.StringUtils;
import com.snail.abell.utils.ThrowableUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Component
@Aspect
@Slf4j
public class LogAspect {

    private final SysLogService logService;

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LogAspect(SysLogService logService) {
        this.logService = logService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.snail.abell.logInterface.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("logPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        logger.info("============= Start =============");
        // 打印请求 url
        logger.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        logger.info("Description    : {}", methodDescription);
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        logger.info("Request Args   : {}", JSONObject.toJSONString(joinPoint.getArgs()));
    }


    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        long startTime = System.currentTimeMillis();
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        //回去当前用户信息
        UserDetails loginUser = SecurityUtils.getCurrentUser();
        SysLog log = new SysLog("INFO",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
       // 打印出参
        logger.info("Response Args  : {}", JSONObject.toJSONString(result));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);

        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //浏览器名称
        String browserName = browser.getName();
        log.setBrowser(browserName);
        logService.save(loginUser.getUsername(), StringUtils.getIp(request), joinPoint, log, SecurityUtils.getCurrentUserId());
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 接口结束后换行，方便分割查看
        logger.info("=================== End =================" + System.lineSeparator());

        SysLog log = new SysLog("ERROR",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        UserDetails loginUser = SecurityUtils.getCurrentUser();
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //浏览器名称
        String browserName = browser.getName();
        log.setBrowser(browserName);
        logService.save(getUsername(),  StringUtils.getIp(request), (ProceedingJoinPoint)joinPoint, log,SecurityUtils.getCurrentUserId());
    }

    public String getUsername() {
        try {
            return SecurityUtils.getCurrentUsername();
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(Log.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

}
