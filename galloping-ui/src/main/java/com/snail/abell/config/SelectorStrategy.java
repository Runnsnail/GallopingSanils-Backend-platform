package com.snail.abell.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;

import java.awt.*;
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

    /**
     *  判断一个元素是否存在
     *
     * @param driver 驱动
     * @param By 传入一个元素       例如：id
     */
    boolean exists(String byValue, WebDriver driver);

    /**
     *  判断一组元素是否存在
     *
     * @param driver 驱动
     * @param By 传入一个元素       例如：id
     */
    boolean elementsExists(String byValue, WebDriver driver);

    /**
     * 1、指定时间内等待直到页面包含文本字符串
     *
     * @param text    期望出现的文本
     * @param seconds 超时时间
     * @return Boolean     检查给定文本是否存在于指定元素中, 超时则捕获抛出异常TimeoutException并返回false
     * @see org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement(WebElement
     * element, String text)
     */
    boolean waitUntilPageContainText(String text, long seconds, WebDriver driver);



   //---------------------------------------元素判断---------------------------------------------------------

    /**
     * 1、指定时间内等待直到元素存在于页面的DOM上并可见, 可见性意味着该元素不仅被显示, 而且具有大于0的高度和宽度
     *
     * @param locator 元素定位器
     * @param seconds 超时时间
     * @return Boolean 检查给定元素的定位器是否出现, 超时则捕获抛出异常TimeoutException并返回false
     * @see org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By
     * locator)
     */
    boolean waitUntilElementVisible(By locator, int seconds, WebDriver driver);

    /**
     * 判断元素是否显示
     * @param value  元素类型
     * @param driver  驱动
     */
    boolean getDisplayState(String value, WebDriver driver);

    /**
     * 判断元素是否可写
     * @param value  元素类型
     * @param driver  驱动
     */
    boolean getEnableState(String value, WebDriver driver);

    /**
     * 判断元素是否选中
     * @param value  元素类型
     * @param driver  驱动
     */
    boolean getSelectState(String value, WebDriver driver);

    /**
     * 获取当前焦点所在页面元素的属性值(name,value,id,src等等)
     * @param attribute   name,value,id,src
     * @param driver  驱动
     */
    String getFocusAttributeValue(String attribute, WebDriver driver);

    /**
     * 等待元素可用再点击
     * @param value   id  xpath ...
     * @param driver
     * @throws InterruptedException
     */
    void waitForEnabledByXpathAndClick(String value, WebDriver driver) throws InterruptedException;

    /**
     *定位元素触发JS点击事件
     * @param value id  xpath ...
     * @param driver
     */
    void clickByJs(String value, WebDriver driver);

    /**
     * 触发JS点击事件
     * @param element  元素
     * @param driver
     */
    void clickByJs(WebElement element, WebDriver driver);

    /**
     * 自定义等待时间
     * @param key
     * @throws InterruptedException
     */
    void sleep(int key) throws InterruptedException;

    /**
     * 滚动到窗口最上方
     * @param driver
     */
    void scrollToTop(WebDriver driver);

    /**
     * 滚动到窗口最下方
     * @param driver
     */
    void scrollToBottom( WebDriver driver);

    /**
     * 滚动到某个元素
     * @param element
     * @param driver
     */
    void scrollToElement(WebElement element, WebDriver driver);

    /**
     * js给指定元素value赋值
     * @param text  赋值文本内容
     * @param element
     * @param driver
     */
    void inputTextByJs(String text, WebElement element, WebDriver driver);

    /**
     * js使元素隐藏元素显示
     * @param element
     * @param driver
     */
    void makeElementDisplay(WebElement element, WebDriver driver);


    //---------------------------------------键盘操作---------------------------------------------------------

    /**
     * 常用keysEnvent事件
     * 1	KeyEvent.VK_PAGE_UP	PgUp （下一页）	33
     * 2	KeyEvent.VK_PAGE_DOWN	PgDn（下一页）	34
     * 3	KeyEvent.VK_HOME	Home 键	36
     * 4	KeyEvent.VK_END	End 键	35
     * 5	KeyEvent.VK_CONTROL	Ctrl 键	17
     * 6	KeyEvent.VK_ALT	Alt 键	18
     * 7	KeyEvent.VK_SPACE	空格 键	32
     * 8	KeyEvent.VK_A	字母 A，VK_A 到 VK_Z 与 ASCII 的 ‘A’ 到 ‘Z’ (0x41 - 0x5A)  对应	65-90
     * 9	KeyEvent.VK_0	数字 0，VK_0 到 VK_9 与 ASCII 的 ‘0’ 到 ‘9’ (0x30 - 0x39) 对应	48-57
     * 10	KeyEvent.VK_F1	F1 键，VK_F1 到 VK_F12 对应键盘上的 F1 到 F12 键	112-123
     * 11	KeyEvent.VK_BACK_SPACE	Backspace 键	8
     * 12	KeyEvent.VK_DOWN	非数字键盘向下箭头键	40
     * 13	KeyEvent.VK_LEFT	非数字键盘向左箭头键	37
     * 14	KeyEvent.VK_UP	非数字键向上箭头键	38
     * 15	KeyEvent.VK_RIGHT	非数字键向右箭头键	39
     * 16	KeyEvent.VK_ENTER	回车键	10
     * 17	KeyEvent.VK_ESCAPE	Esc 建	27
     * 18	KeyEvent.VK_INSERT	Insert 键	155
     * 19	KeyEvent.VK_SHIFT	Shift 键	16
     * 20	KeyEvent.VK_TAB	Tab 键	9
     * 21	KeyEvent.VK_WINDOWS	"Windows" 键	524
     *
     */

    /**
     *    常用keycode事件
     *     void delay (int ms)  睡眠指定的时间（类似于线程中sleep）
     *
     *     void keyPress（int keycode） 按下指定的键
     *
     *     void keyRelease（int keycode） 释放指定的键
     *
     *     void mouseMove（int x，int y） 将鼠标移动到给定的屏幕坐标上
     *
     *     void mousePress（int buttons） 按下一个或多个鼠标按键
     *
     *     void mouseRelease（int buttons） 释放一个活多个鼠标按键
     *
     *     void mouseWheel（int wheelAmt） 滚动鼠标滑轮
     */

    /**
     *  获取键盘
     * @param driver
     * @return
     */
    Keyboard getKeyboard(WebDriver driver);
    /**
     *     按物理按键(KeyEvent类中查找相关的常量)
     *     例子：
     *        Robot robot = new Robot();
     *         robot.keyPress(KeyEvent.VK_ENTER);//按下enter键
     * @deprecated  常用keycode事件
     */
    void pressKeyEvent(int keycode) throws AWTException;

    /**
     *     按物理按键(KeyEvent类中查找相关的常量)
     *     例子：
     *        Robot robot = new Robot();
     *         robot.keyRelease(KeyEvent.VK_ENTER);//松开enter键
     *     @deprecated  常用keycode事件 同上
     */
    void releaseKeyEvent(int keycode) throws AWTException;

    /**
     * 模拟键盘组合操作
     * @param driver
     * @param keysEnvent   列如：Keys.CONTROL
     * @param keysValue    列如：Keys.F5
     */
    void simulationKeyboardOperation(WebDriver driver,String keysCodeOne,String keysCodeTwo) throws AWTException;

    /**
     * 模拟键盘操作
     * @param driver
     * @param keysEnvent
     * @throws AWTException
     */
    void keyboardOperation(WebDriver driver, String keyscode) throws AWTException;

    //---------------------------------------鼠標操作---------------------------------------------------------

    /**
     * 鼠標悬浮
     * @param value
     * @param driver
     */
    void moveToElementBy(String value, WebDriver driver);

    /**
     * 鼠標右键点击
     * @param value
     * @param driver
     */
    void rightClickWebElement(String value, WebDriver driver);

    /**
     * 鼠標左键点击
     * @param value
     * @param driver
     */
    void leftClickWebElement(String value, WebDriver driver);

    /**
     * 鼠標右键双击
     * @param value
     * @param driver
     */
    void doubleClickWebElement(String value, WebDriver driver);

    /**
     * 模拟键盘输入关键字到输入框
     * @param by 定位方式
     * @param text  输入的文本
     * @param driver
     */
    void sendText(By by, String text, WebDriver driver);

    /**
     * 模拟鼠标移动到指定元素,并点击
     * @param by
     * @param text
     * @param driver
     */
    void moveToElementAndClick(String value, WebDriver driver);

    /**
     * 模拟鼠标点击和释放
     * @param by
     * @param driver
     * @param time 时间
     */
    void clickHoldAndRelease(String value, WebDriver driver);
}
