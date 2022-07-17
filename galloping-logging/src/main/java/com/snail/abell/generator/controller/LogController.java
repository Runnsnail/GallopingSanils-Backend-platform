package com.snail.abell.generator.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snail.abell.apiInterface.ResponseResult;
import com.snail.abell.generator.dto.LogErrorDTO;
import com.snail.abell.generator.dao.SysLogMapper;
import com.snail.abell.generator.pojo.LogMessageBean;
import com.snail.abell.generator.pojo.SysLog;
import com.snail.abell.generator.service.SysLogService;
import com.snail.abell.logInterface.Log;
import com.snail.abell.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseResult
@RequestMapping("/api/logs")
@Api(tags = "系统：日志管理")
public class LogController {

    private final SysLogService logService;

    private final SysLogMapper sysLogMapper;

    private final String INFOR_PREFIX = "INFO";

    private final String ERROR_PREFIX = "ERROR";

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check()")
    public void exportLog(HttpServletResponse response, Integer pageNum,Integer pageSum) throws IOException {
        Page<SysLog> page = new Page<>(pageNum,pageSum);
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLog::getLogType,INFOR_PREFIX);
        List<SysLog> list = sysLogMapper.selectPage(page,wrapper).getRecords();
        logService.download(list, response,INFOR_PREFIX);
    }

    @Log("导出错误数据")
    @ApiOperation("导出错误数据")
    @GetMapping(value = "/error/download")
    @PreAuthorize("@el.check()")
    public void exportErrorLog(HttpServletResponse response, Integer pageNum,Integer pageSum) throws IOException {
        Page<SysLog> page = new Page<>(pageNum,pageSum);
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLog :: getLogType,ERROR_PREFIX);
        List<SysLog> list = sysLogMapper.selectPage(page,wrapper).getRecords();
        logService.download(list, response,ERROR_PREFIX);
    }
    @GetMapping
    @ApiOperation("日志查询")
    @PreAuthorize("@el.check()")
    public List<LogMessageBean> queryLog(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSum){
        Page<SysLog> page = new Page<>(pageNum,pageSum);
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLog :: getLogType,INFOR_PREFIX);
        List<SysLog> list = sysLogMapper.selectPage(page,wrapper).getRecords();
        return logService.queryAllInfor(list);
    }

    @GetMapping(value = "/user")
    @ApiOperation("用户日志查询")
    public List<SysLog> queryUserLog(){
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLog :: getUsername, SecurityUtils.getCurrentUsername());
        return sysLogMapper.selectList(wrapper);
    }

    @GetMapping(value = "/error")
    @ApiOperation("错误日志查询")
    @PreAuthorize("@el.check()")
    public List<LogErrorDTO> queryErrorLog(@RequestParam(value = "pageNum") Integer pageNum,@RequestParam(value = "pageSize") Integer pageSum){
        Page<SysLog> page = new Page<>(pageNum,pageSum);
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysLog :: getLogType,ERROR_PREFIX);
        List<SysLog> list = sysLogMapper.selectPage(page,wrapper).getRecords();
        return logService.queryAllError(list);
    }

    @GetMapping(value = "/error/{id}")
    @ApiOperation("日志异常详情查询")
    @PreAuthorize("@el.check()")
    public String queryErrorLogDetail(@PathVariable Long id){
        return logService.queryErrorLogDetail(id);
    }



    @DeleteMapping(value = "/del/error")
    @Log("删除所有ERROR日志")
    @ApiOperation("删除所有ERROR日志")
    @PreAuthorize("@el.check()")
    public boolean delAllErrorLog(){
        return  logService.delAllByError(ERROR_PREFIX);
    }

    @DeleteMapping(value = "/del/info")
    @Log("删除所有INFO日志")
    @ApiOperation("删除所有INFO日志")
    @PreAuthorize("@el.check()")
    public boolean delAllInfoLog(){
        return  logService.delAllByInfo(INFOR_PREFIX);
    }
}
