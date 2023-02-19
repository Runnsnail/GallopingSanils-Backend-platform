package com.snail.abell.elementTypeHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.awt.*;

import static org.openqa.selenium.Keys.DELETE;

/**
 * @author Abell
 * @date 2022/11/27
 */
@Service
public class KeyboardHandle {


    public void keyboardOperation(WebDriver driver, String keysCode) throws AWTException {

        switch (keysCode){
            case "复制":
                driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL, "c");
                break;
            case "粘贴":
                driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL, "v");
                break;
            case "全选":
                driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL, "a");
                break;
            case "剪切":
                driver.findElement(By.id("kw")).sendKeys(Keys.CONTROL, "x");
                break;
            case "方向键左":
                driver.findElement(By.id("kw")).sendKeys(Keys.LEFT);
                break;
            case "方向键右":
                driver.findElement(By.id("kw")).sendKeys(Keys.RIGHT);
                break;
            case "方向键上":
                driver.findElement(By.id("kw")).sendKeys(Keys.UP);
                break;
            case "方向键下":
                driver.findElement(By.id("kw")).sendKeys(Keys.DOWN);
                break;
            case "回车键":
                driver.findElement(By.id("kw")).sendKeys(Keys.ENTER);
                break;
            case "删除键":
                driver.findElement(By.id("kw")).sendKeys(DELETE);
                break;
            default:
                break;

        }

    }

}
