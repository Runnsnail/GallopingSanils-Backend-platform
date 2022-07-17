package com.snail.abell.logInterface;


import com.snail.abell.generator.dto.LogErrorDTO;
import com.snail.abell.generator.pojo.LogMessageBean;
import com.snail.abell.generator.pojo.SysLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface LogMapper {

    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    LogMessageBean toLogMessage(SysLog sysLog);

    LogErrorDTO toLogError(SysLog sysLog);

    List<LogErrorDTO> toLogErrorList(List<SysLog> sysLogs);

    List<LogMessageBean> toLogMessageList(List<SysLog> sysLogs);
}
