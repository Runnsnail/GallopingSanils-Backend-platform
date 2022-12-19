package com.snail.abell.elementTypeHandler;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Abell
 * @date 2022/11/11
 */
public class JavaScriptHandle {


    public void inputTextByJs(String text, WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor ) driver;
        js.executeScript("arguments[0].value=" + text + "\"", element);
    }

    public void makeElementDisplay(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style=arguments[1]", element, "display: block;");
    }
}
