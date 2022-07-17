package com.snail.abell.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.generator.dto.LogErrorDTO;
import com.snail.abell.generator.pojo.LogMessageBean;
import com.snail.abell.generator.pojo.SysLog;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
* @author huawei
* @description 针对表【sys_log(系统日志)】的数据库操作Service
* @createDate 2022-05-29 11:08:37
*/
public interface SysLogService extends IService<SysLog> {

    /**
     * 保存日志数据
     * @param username 用户
     * @param ip 请求IP
     * @param joinPoint /
     * @param log 日志实体
     */
    @Async
    void save(String username, String ip, ProceedingJoinPoint joinPoint, SysLog log, Long currentUserId);
    /**
     * @description: 操作日志
     * @param httpServletRequest
     * @param map
     * @return: com.guangyi.project.model.system.MessageBean
     */
    LogMessageBean operateLog(HttpServletRequest httpServletRequest, Map<String, Object> map);

    void download(List<SysLog> list, HttpServletResponse response,String type) throws IOException;

    List<LogMessageBean> queryAllInfor(List<SysLog> list);

    List<LogErrorDTO>  queryAllError(List<SysLog> list);

    String queryErrorLogDetail(Long id);

    boolean delAllByError(String error);

    boolean delAllByInfo(String infor);
}
