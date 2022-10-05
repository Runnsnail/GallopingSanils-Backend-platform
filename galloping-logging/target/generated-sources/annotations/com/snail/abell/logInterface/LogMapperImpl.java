package com.snail.abell.logInterface;

import com.snail.abell.generator.dto.LogErrorDTO;
import com.snail.abell.generator.pojo.LogMessageBean;
import com.snail.abell.generator.pojo.SysLog;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-30T10:29:10+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
public class LogMapperImpl implements LogMapper {

    @Override
    public LogMessageBean toLogMessage(SysLog sysLog) {
        if ( sysLog == null ) {
            return null;
        }

        LogMessageBean logMessageBean = new LogMessageBean();

        logMessageBean.setId( sysLog.getId() );
        logMessageBean.setDescription( sysLog.getDescription() );
        logMessageBean.setExceptionDetail( sysLog.getExceptionDetail() );
        logMessageBean.setMethod( sysLog.getMethod() );
        logMessageBean.setParams( sysLog.getParams() );
        logMessageBean.setRequestIp( sysLog.getRequestIp() );
        logMessageBean.setUsername( sysLog.getUsername() );
        logMessageBean.setAddress( sysLog.getAddress() );

        return logMessageBean;
    }

    @Override
    public LogErrorDTO toLogError(SysLog sysLog) {
        if ( sysLog == null ) {
            return null;
        }

        LogErrorDTO logErrorDTO = new LogErrorDTO();

        logErrorDTO.setId( sysLog.getId() );
        logErrorDTO.setUsername( sysLog.getUsername() );
        logErrorDTO.setDescription( sysLog.getDescription() );
        logErrorDTO.setMethod( sysLog.getMethod() );
        logErrorDTO.setParams( sysLog.getParams() );
        logErrorDTO.setBrowser( sysLog.getBrowser() );
        logErrorDTO.setRequestIp( sysLog.getRequestIp() );
        logErrorDTO.setAddress( sysLog.getAddress() );
        logErrorDTO.setCreatedate( sysLog.getCreatedate() );

        return logErrorDTO;
    }

    @Override
    public List<LogErrorDTO> toLogErrorList(List<SysLog> sysLogs) {
        if ( sysLogs == null ) {
            return null;
        }

        List<LogErrorDTO> list = new ArrayList<LogErrorDTO>( sysLogs.size() );
        for ( SysLog sysLog : sysLogs ) {
            list.add( toLogError( sysLog ) );
        }

        return list;
    }

    @Override
    public List<LogMessageBean> toLogMessageList(List<SysLog> sysLogs) {
        if ( sysLogs == null ) {
            return null;
        }

        List<LogMessageBean> list = new ArrayList<LogMessageBean>( sysLogs.size() );
        for ( SysLog sysLog : sysLogs ) {
            list.add( toLogMessage( sysLog ) );
        }

        return list;
    }
}
