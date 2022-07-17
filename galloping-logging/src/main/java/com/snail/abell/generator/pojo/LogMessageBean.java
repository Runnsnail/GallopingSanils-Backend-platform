package com.snail.abell.generator.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


/**
 * @author Abell
 */
@Data
public class LogMessageBean {


    @Excel(name = "id", orderNum = "0")
    private Long id;
    /**
     * 描述
     */
    @Excel(name="详细描述",orderNum = "7")
    private String description;

    /**
     * 异常详细
     */
    @Excel(name="异常详细",orderNum = "1")
    private String exceptionDetail;


    /**
     * 方法名
     */
    @Excel(name="方法名",orderNum = "2")
    private String method;

    /**
     * 参数
     */
    @Excel(name="参数",orderNum = "3")
    private String params;

    /**
     * 请求ip
     */
    @Excel(name="请求ip",orderNum = "4")
    private String requestIp;


    /**
     * 用户名
     */
    @Excel(name="用户名",orderNum = "5")
    private String username;

    /**
     * 地址
     */
    @Excel(name="地址",orderNum = "6")
    private String address;




}
