package com.snail.abell.elementTypeHandler;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

/**
 * @author Abell
 * @date 2022/11/11
 */
@Component
public class JavaScriptHandle {

    /**
     * @deprecated  js给指定元素value赋值
     * @param text  文本内容
     * @param element 元素
     * @param driver 驱动
     */
    public void inputTextByJs(String text, WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor ) driver;
        js.executeScript("arguments[0].value=" + text + "\"", element);
    }

    /**
     * @deprecated Js点击
     * @param element 元素
     * @param driver 驱动
     */
    public void clickByJs(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    /**
     * @deprecated js使元素隐藏元素显示
     * @param element 元素
     * @param driver 驱动
     */
    public void makeElementDisplay(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style=arguments[1]", element, "display: block;");
    }


    /**
     * @deprecated 滚动到最上面
     * @param driver 驱动
     */
    public void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
    }

    /**
     * @deprecated 滚动到最下面
     * @param driver 驱动
     */
    public void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,10000);");
    }

    /**
     * @deprecated 滚动到指定元素
     * @param element 元素
     * @param driver 驱动
     */
    public void scrollToElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
