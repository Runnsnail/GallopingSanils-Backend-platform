package com.snail.abell.elementTypeHandler;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author Abell
 * @date 2022/11/9
 */
public class BrowserHandle {

    private  final Logger logger = LoggerFactory.getLogger(BrowserHandle.class);
    private  long timeOutInSeconds = 10;
    WebDriver driver = null;

    public  WebDriver initBrowser(String browser) {

        switch (browser) {
            case "FireFox":
                System.setProperty("webdriver.gecko.driver", "D:\\2345Downloads\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\huawei\\Downloads\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                try {
                    throw new Exception("浏览器错误!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return driver;
    }

    /**
     *     指定浏览器打开URL
     */
    public  WebDriver openBrowser(String browser,String driverUrl) {
        driver = initBrowser(browser);
        driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
        driver.get(driverUrl);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     *     关闭当前浏览器
     */
    public  void closeCurrentBrowser() {
        driver.close();
    }
    /**
     *     关闭所有selenium驱动打开的浏览器
     */
    public  void closeAllBrowser() {
        driver.quit();
    }
    /**
     *     最大化浏览器
     */
    public  void maxBrowser() {
        driver.manage().window().maximize();
    }

    /**
     *     自定义设置浏览器尺寸
     */
    public  void setBrowserSize(int width, int heigth) {
        driver.manage().window().setSize(new Dimension(width, heigth));
    }

    /**
     *     获取网页的title值
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     *     获取当前url字符串
     */
    public  String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     *    上一个页面(点击浏览器返回)
     */
    public  void returnToPreviousPage() {
        driver.navigate().back();
    }

    /**
     *     下一个页面(如果没有下一个页面则什么都不做)
     *     浏览器上的前进
     */
    public  void forwardToNextPage() {
        driver.navigate().forward();
    }

    /**
     *     刷新页面
     */
    public  void refreshPage() {
        driver.navigate().refresh();
    }

    /***
     * 切换iframe
     * @param cssSelector  css选择器
     */
    public void changeIframe(String cssSelector){
        WebElement iframe1 = driver.findElement(By.cssSelector(cssSelector));
        driver.switchTo().frame(iframe1);
    }

    /***
     * 跳出iframe
     */
    public void jumpIframe(){
        driver.switchTo().defaultContent();
    };

    /***
     * 当只有两个窗口时进行窗口切换
     */
    public void changeTwoWindow(){
        String mainHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String hand : handles) {
            if (!hand.equals(mainHandle)) {
                driver.switchTo().window(hand);
                break;
            }
        }
    };

    /**
     * 根据title进行窗口切换
     * @param title
     */
    public void changeSomeWindow(String title ){
        Set<String> handles = driver.getWindowHandles();
        for (String hand : handles) {
            if (driver.switchTo().window(hand).getTitle().equals(title)) {
                driver.switchTo().window(hand);
                break;
            }
        }
    };


    /**
     * @deprecated  强制刷新页面
     */
    public void refresh() {
        Actions ctrl = new Actions(driver);
        ctrl.keyDown(Keys.CONTROL).perform();
        try {
            pressKeyEvent(KeyEvent.VK_F5);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        ctrl.keyUp(Keys.CONTROL).perform();
    }

    /**
     *     判断是否加载有JQuery
     */
    public Boolean jQueryLoaded() {
        Boolean loaded;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            loaded = (Boolean) js.executeScript("return" + "JQuery()!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    /**
     *     按物理按键(KeyEvent类中查找相关的常量)
     *     例子：
     *        Robot robot = new Robot();
     *         robot.keyPress(KeyEvent.VK_ENTER);//按下enter键
     */
    public void pressKeyEvent(int keycode) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(keycode);
    }

    /**
     *  确认弹窗操作
     * @param driver 驱动
     */
    public void confirmAlter(WebDriver driver)  {
        driver.switchTo().alert().accept();
    }

    /**
     * 取消弹窗
     * @param driver 驱动
     */
    public void cancleAlter(WebDriver driver)  {
        driver.switchTo().alert().dismiss();
    }

}
