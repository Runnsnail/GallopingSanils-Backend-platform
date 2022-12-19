package com.snail.abell.elementTypeHandler;

import com.snail.abell.config.SelectorStrategy;
import com.snail.abell.config.SelectorTypeAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Abell
 * @date 2022/11/11
 */
@SelectorTypeAnnotation(selectorType = SelectorType.CLASS)
public class SelectorByClassStrategy implements SelectorStrategy {

    private static final Logger logger = LoggerFactory.getLogger(SelectorByClassStrategy.class);

    @Override
    public WebElement findElement(String byValue, WebDriver driver) {
        return driver.findElement(By.className(byValue));
    }

    @Override
    public List<WebElement> findElements(String byValue, WebDriver driver) {
        return driver.findElements(By.className(byValue));
    }

    @Override
    public boolean findElementClick(String byValue, WebDriver driver) {
        driver.findElement(By.className(byValue)).click();
        return true;
    }

    @Override
    public boolean findElementClear(String byValue, WebDriver driver) {
        driver.findElement(By.className(byValue)).clear();
        return true;
    }

    @Override
    public boolean findElementClearAndSendKeys(String byValue, String key, WebDriver driver) {
        findElementClear( byValue,  driver);
        driver.findElement(By.className(byValue)).sendKeys(key);
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
                driver.findElement(By.className(byValue)).click();
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean exists(String byValue, WebDriver driver) {
        // 设置查询组件等待时间
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            driver.findElement(By.className(byValue));
            // 设置查询组件等待时间
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            // 设置查询组件等待时间
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return false;
        }
    }

    @Override
    public boolean elementsExists(String byValue, WebDriver driver) {
        return driver.findElements(By.className(byValue)).size() > 0;
    }

    @Override
    public boolean waitUntilPageContainText(String text, long seconds, WebDriver driver) {
        try {
            return new WebDriverWait(driver, seconds)
                    .until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.tagName("body")), text));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean waitUntilElementVisible(By locator, int seconds, WebDriver driver) {
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean getDisplayState(String value, WebDriver driver) {
        return driver.findElement(By.className(value)).isDisplayed();
    }

    @Override
    public boolean getEnableState(String value, WebDriver driver) {
        return driver.findElement(By.className(value)).isEnabled();
    }

    @Override
    public boolean getSelectState(String value, WebDriver driver) {
        return driver.findElement(By.className(value)).isSelected();
    }

    @Override
    public String getFocusAttributeValue(String attribute, WebDriver driver) {
        String value = "";
        try {
            Thread.sleep(333);
        } catch (Exception e) {
            e.printStackTrace();
        }
        value = driver.switchTo().activeElement().getAttribute(attribute);
        logger.info("The focus Element's " + attribute + "attribute value is>>" + value);
        return value;
    }

    @Override
    public void waitForEnabledByXpathAndClick(String value, WebDriver driver) throws InterruptedException {
        boolean key = true;
        while (key) {
            if (findElement(value,driver).isEnabled() && findElement(value,driver).isDisplayed()) {
                clickByJs(value,driver);
                key = false;
            } else {
                sleep(0);
            }
        }
    }

    @Override
    public void clickByJs(String value, WebDriver driver) {
        clickByJs(driver.findElement(By.className(value)),driver);
    }

    @Override
    public void clickByJs(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    @Override
    public void sleep(int key) throws InterruptedException {
        switch (key) {
            case 1:
                Thread.sleep(1000);
                break;
            case 2:
                Thread.sleep(2000);
                break;
            case 5:
                Thread.sleep(5000);
                break;
            case 10:
                Thread.sleep(10000);
                break;
            default:
                System.out.println("错误");
                break;
        }
    }

    @Override
    public void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
    }

    @Override
    public void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,10000);");
    }

    @Override
    public void scrollToElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Override
    public void inputTextByJs(String text, WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor ) driver;
        js.executeScript("arguments[0].value=" + text + "\"", element);
    }

    @Override
    public void makeElementDisplay(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style=arguments[1]", element, "display: block;");
    }

    @Override
    public Keyboard getKeyboard(WebDriver driver) {
        return ((HasInputDevices) driver).getKeyboard();
    }

    @Override
    public void pressKeyEvent(int keycode) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(keycode);
    }

    @Override
    public void releaseKeyEvent(int keycode) throws AWTException {
        Robot robot = new Robot();
        robot.keyRelease(keycode);
    }

    @Override
    public void simulationKeyboardOperation(WebDriver driver, String keysCodeOne, String keysCodeTwo) throws AWTException {
        driver.findElement(By.className("kw")).sendKeys(keysCodeOne, keysCodeTwo);
    }

    @Override
    public void keyboardOperation(WebDriver driver, String keysCode) throws AWTException {
        driver.findElement(By.className("kw")).sendKeys(keysCode);
    }

    @Override
    public void moveToElementBy(String value, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.className(value))).perform();
    }

    @Override
    public void rightClickWebElement(String value, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(By.className(value))).perform();
    }

    @Override
    public void leftClickWebElement(String value, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(By.className(value))).perform();
    }


    @Override
    public void doubleClickWebElement(String value, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(By.className(value))).perform();
    }

    @Override
    public void sendText(By by, String text, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(by),text).perform();
    }

    @Override
    public void moveToElementAndClick(String value, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.className(value))).click().perform();
    }

    @Override
    public void clickHoldAndRelease(String value, WebDriver driver, Long time) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(By.className(value))).perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.release(driver.findElement(By.className(value))).perform();
    }
}
