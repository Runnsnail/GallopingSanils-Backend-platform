package com.snail.abell.generator.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.generator.dto.LogErrorDTO;
import com.snail.abell.generator.pojo.LogMessageBean;
import com.snail.abell.generator.pojo.SysLog;
import com.snail.abell.generator.service.SysLogService;
import com.snail.abell.generator.dao.SysLogMapper;
import com.snail.abell.logInterface.LogMapper;
import com.snail.abell.utils.ExcelStyleUtil;
import com.snail.abell.utils.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huawei
 * @description 针对表【sys_log(系统日志)】的数据库操作Service实现
 * @createDate 2022-05-29 11:08:37
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Resource
    private SysLogMapper sysLogMap;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String ip, ProceedingJoinPoint joinPoint, SysLog log, Long currentUserId) {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.snail.abell.logInterface.Log aopLog = method.getAnnotation(com.snail.abell.logInterface.Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null && argValues.length > 1) {
            for (int i = 1; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        } else {
            params.append("无参数");
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        //类型 0-后台 1-APP
        log.setType(aopLog.type());
        if (currentUserId != null) {
            log.setUserId(currentUserId);
        }
        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                assert argValues != null;
                username = new JSONObject(argValues[0]).get("username").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        log.setChangedate(new Date());
        log.setCreatedate(new Date());
        log.setChangeuser(username);
        log.setCreateuser(username);
        log.setIsDel(0);
        sysLogMap.insert(log);

    }

    @Override
    public LogMessageBean operateLog(HttpServletRequest httpServletRequest, Map<String, Object> map) {
        LogMessageBean result = new LogMessageBean();
        return null;
    }

    @Override
    public void download(List<SysLog> list, HttpServletResponse response, String type) throws IOException {
        List exportList = new ArrayList();
        if ("INFOR" == type) {
            List<LogMessageBean> listMessageBeans = LogMapper.INSTANCE.toLogMessageList(list);
            createExcel(response,listMessageBeans,LogMessageBean.class );
        } else if ("ERROR" == type) {
            List<LogErrorDTO> logErrorDTOS = LogMapper.INSTANCE.toLogErrorList(list);
            createExcel(response,logErrorDTOS,LogErrorDTO.class );
        }
    }

    @Override
    public List<LogMessageBean> queryAllInfor(List<SysLog> list) {
            List<LogMessageBean> listMessageBeans = LogMapper.INSTANCE.toLogMessageList(list);
        return listMessageBeans;
    }

    @Override
    public List<LogErrorDTO> queryAllError(List<SysLog> list) {
        List<LogErrorDTO> logErrorDTOS = LogMapper.INSTANCE.toLogErrorList(list);
        return logErrorDTOS;
    }

    @Override
    public String queryErrorLogDetail(Long id) {
        return sysLogMap.selectById(id).getExceptionDetail();
    }

    @Override
    public boolean delAllByError(String error) {
       int num = sysLogMap.delete(new QueryWrapper<SysLog>().eq("logType",error));
       return num > 0;
    }

    @Override
    public boolean delAllByInfo(String infor) {
        int num =  sysLogMap.delete(new QueryWrapper<SysLog>().eq("logType",infor));
        return num > 0;
    }

    ;

    public static void createExcel(HttpServletResponse response,List list,Class< ? > clazz) throws IOException {
        //生成excel
        ExportParams exportParams = new ExportParams("基本日志信息", "详细信息");
        exportParams.setStyle(ExcelStyleUtil.class);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, clazz, list);
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("参数日志文件.xls", "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }
};
