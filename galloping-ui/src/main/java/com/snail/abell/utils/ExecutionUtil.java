package com.snail.abell.utils;

import com.snail.abell.elementTypeHandler.BrowserHandle;
import com.snail.abell.elementTypeHandler.CookerHandle;
import com.snail.abell.entity.TStepUiNew;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Abell
 * @date 2023/1/17
 */
public class ExecutionUtil {

    /**
     * @deprecated 浏览器方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerBrowerMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())&& stepUiNew.isEnable()) {
            method.invoke(BrowserHandle.class,driver);
        }{
            method.invoke(BrowserHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }

    /**
     * @deprecated cooker方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerCookerTypeMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())) {
            method.invoke(CookerHandle.class);
        }{
            method.invoke(CookerHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }

    /**
     * @deprecated 浏览器方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerElementTypeMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())) {
            method.invoke(BrowserHandle.class);
        }{
            method.invoke(BrowserHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }

    /**
     * @deprecated 浏览器方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerJsMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())) {
            method.invoke(BrowserHandle.class);
        }{
            method.invoke(BrowserHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }

    /**
     * @deprecated 浏览器方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerAlterTypeMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())) {
            method.invoke(BrowserHandle.class);
        }{
            method.invoke(BrowserHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }

    /**
     * @deprecated 浏览器方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerKetboardMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())) {
            method.invoke(BrowserHandle.class);
        }{
            method.invoke(BrowserHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }

    /**
     * @deprecated 浏览器方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerMouseMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())) {
            method.invoke(BrowserHandle.class);
        }{
            method.invoke(BrowserHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }

    /**
     * @deprecated 浏览器方法调用
     * @param method    获取的方法
     * @param stepUiNew 步骤对象
     * @return 执行结果  执行状态 0成功 1失败 2跳过
     */
    public int handlerFileTypeMethods(Method method, TStepUiNew stepUiNew, WebDriver driver) throws InvocationTargetException, IllegalAccessException {
        int status = 0;
        if (StringUtils.isNotBlank(stepUiNew.getOptionData())) {
            method.invoke(BrowserHandle.class);
        }{
            method.invoke(BrowserHandle.class,stepUiNew.getOptionData(),driver);
        }
        return status;
    }


}
