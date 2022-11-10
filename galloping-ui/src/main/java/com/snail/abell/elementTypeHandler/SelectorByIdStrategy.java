package com.snail.abell.elementTypeHandler;

import com.snail.abell.config.SelectorStrategy;
import com.snail.abell.config.SelectorTypeAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Abell
 * @date 2022/11/9
 */
@SelectorTypeAnnotation(selectorType = SelectorType.ID)
public class SelectorByIdStrategy implements SelectorStrategy {

    private static final Logger logger = LoggerFactory.getLogger(SelectorByIdStrategy.class);

    @Override
    public WebElement findElement(String byValue, WebDriver driver) {
        return driver.findElement(By.id(byValue));
    }

    @Override
    public List<WebElement> findElements(String byValue, WebDriver driver) {
        return driver.findElements(By.id(byValue));
    }

    @Override
    public boolean findElementClick(String byValue, WebDriver driver) {
        driver.findElement(By.id(byValue)).click();
        return true;
    }

    @Override
    public boolean findElementClear(String byValue, WebDriver driver) {
        driver.findElement(By.id(byValue)).clear();
        return true;
    }

    @Override
    public boolean findElementClearAndSendKeys(String byValue, String key, WebDriver driver) {
        findElementClear( byValue,  driver);
        driver.findElement(By.id(byValue)).sendKeys(key);
        return true;
    }

    @Override
    public String getText(String byValue,WebDriver driver) {
        return findElement( byValue, driver).getText();
    }

    @Override
    public boolean click(String byValue, int clickCount, WebDriver driver) {
        try {
            for (int i = 0; i < clickCount; i++) {
                driver.findElement(By.id(byValue)).click();
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
