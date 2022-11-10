package com.snail.abell.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author Abell
 * @date 2022/11/9
 * @deprecated 定义策略模式
 */
@SuppressWarnings("all")
public interface SelectorStrategy {

    /**
     * 查找单个元素
     *
     * @param driver 驱动
     * @param byValue 传入一个类型值       例如：username
     */
    WebElement findElement( String byValue, WebDriver driver);

    /**
     * 查找一组元素
     *
     * @param driver 驱动
     * @param byValue 传入一个类型值       例如：username
     */
    List<WebElement> findElements( String byValue, WebDriver driver);

    /**
     * 查找元素并点击
     * @param driver 驱动
     * @param byValue 传入一个类型值       例如：username
     */
    boolean findElementClick( String byValue, WebDriver driver);

    /**
     * 查找元素并清除文本内容
     *
     * @param driver 驱动
     * @param byValue 传入一个类型值       例如：username
     */
    boolean findElementClear( String byValue, WebDriver driver);

    /**
     * 查找元素并输入值
     *
     * @param driver 驱动
     * @param byValue 传入一个类型值       例如：username
     * @param key     填写要输入的值        例如：zhangsan
     */
    boolean findElementClearAndSendKeys( String byValue, String key, WebDriver driver);

    /**
     * 定位元素，并获取其文本内容
     * @param driver 驱动
     * @param byValue 传入一个类型值       例如：username
     */
    String getText( String byValue,WebDriver driver);

    /**
     * 定位元素，并指定点击次数(连续点击)
     *
     * @param driver 驱动
     * @param byValue 传入一个类型值       例如：username
     * @param clickCount     点击次数        例如：2
     */
    boolean click(String byValue, int clickCount, WebDriver driver);
}
