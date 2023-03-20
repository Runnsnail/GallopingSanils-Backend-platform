package com.snail.abell.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * @author Abell
 * @date 2023/2/23
 */
public class PreconditionsUtil {


    //---------------------------------------元素判断---------------------------------------------------------

    /**
     *  判断一个元素是否存在
     *
     * @param driver 驱动
     * @param byValue 传入一个元素       例如：id
     */
    public static boolean exists(String byValue, WebDriver driver) {
        // 设置查询组件等待时间
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            driver.findElement(By.xpath(byValue));
            // 设置查询组件等待时间
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            // 设置查询组件等待时间
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return false;
        }
    }


    /**
     * @deprecated 一组元素是否存在
     * @param byValue 定位
     * @param driver 驱动
     * @return 结果
     */
    public static boolean elementsExists(String byValue, WebDriver driver) {
        return driver.findElements(By.xpath(byValue)).size() > 0;
    }


    /**
     * 判断元素是否显示
     * @param value  元素类型
     * @param driver  驱动
     */
    public static boolean getDisplayState(String value, WebDriver driver) {
        return driver.findElement(By.xpath(value)).isDisplayed();
    }

    /**
     * 判断元素是否可写
     * @param value  元素类型
     * @param driver  驱动
     */
    public static boolean getEnableState(String value, WebDriver driver) {
        return driver.findElement(By.xpath(value)).isEnabled();
    }


    /**
     * 判断元素是否选中
     * @param value  元素类型
     * @param driver  驱动
     */
    public static boolean getSelectState(String value, WebDriver driver) {
        return driver.findElement(By.xpath(value)).isSelected();
    }


    /**
     * 等待元素可用再点击
     * @param value   id  xpath ...
     * @param driver
     * @throws InterruptedException
     */
    public static void waitForEnabledByXpathAndClick(String value, WebDriver driver) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElement(value, driver).isEnabled() && findElement(value, driver).isDisplayed()) {
                clicksByJs(value, driver);
                key = false;
            }else {
                Thread.sleep(0);
            }
        }
    }

    public static WebElement findElement(String byValue, WebDriver driver) {
        return driver.findElement(By.xpath(byValue));
    }

    public static void clicksByJs(String value, WebDriver driver) {
        clickByJs(driver.findElement(By.xpath(value)),driver);
    }

    public static void clickByJs(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    //---------------------------------------文本断言---------------------------------------------------------


}
