package com.snail.abell.config;

/**
 * @author Abell
 * @date 2023/1/15
 */
public enum MethodsNameByKey {


    //    BrowserHandleMethod: 处理方法浏览器方法
    OPENBROWSER("打开URL", "openBrowser"),
    CLOSECURRENTBROWSER("关闭浏览器", "closeCurrentBrowser"),
    CLOSEALLBROWSER("关闭所有打开的浏览器", "closeAllBrowser"),
    MAXBROWSER("最大化浏览器", "maxBrowser"),
    SETBROWSERSIZE("自定义设置浏览器尺寸", "setBrowserSize"),
    GETTITLE("", "getTitle"),
    GETURL("", "getUrl"),
    RETURNTOPREVIOUSPAGE("上一个页面", "returnToPreviousPage"),
    FORWARDTONEXTPAGE("下一个页面", "forwardToNextPage"),
    REFRESHPAGE("刷新页面", "refreshPage"),
    CHANGEIFRAME("切换iframe", "changeIframe"),
    JUMPIFRAME("跳出iframe", "jumpIframe"),
    CHANGETWOWINDOW("窗口切换(限制2窗口)", "changeTwoWindow"),
    CHANGESOMEWINDOW("根据title进行窗口切换", "changeSomeWindow"),
    REFRESH("强制刷新页面", "refresh"),
    JQUERYLOADED("判断是否加载有JQuery", "jQueryLoaded"),
    PRESSKEYEVENT("按物理按键", "pressKeyEvent"),
    CONFIRMALTER("确认弹窗", "confirmAlter"),
    CANCLEALTER("取消弹窗", "cancleAlter"),

    //    CookerHandleMethod: 处理方法cooker方法
    GETALLCOOKIES("获取当前域所有的cookies", "getAllCookies"),
    OUTPUTCOOKIE("输出cookies信息", "outputCookie"),
    ADDCOOKIES("添加cookies信息", "addCookies"),
    ADDCOOKIE("添加cookie信息", "addCookies"),
    ADDCOOKIEBYNAME("用给定的name和value创建指定路径的Cookie并添加", "addCookieByName"),
    DELETECOOKIE("根据cookie名称删除cookie", "deleteCookie"),
    DELETEALLCOOKIES("删除当前域的所有Cookie", "deleteAllCookies"),
    GETCOOKIEBYNAME("根据名称获取指定cookie", "getCookieByNameAndValue"),

    //    JavaScriptHandleMethod: 处理方法JS方法
    INPUTTEXTBYJS("js给指定元素value赋值", "inputTextByJs"),
    CLICKBYJS("JS点击", "clickByJs"),
    MAKEELEMENTDISPLAY("js使元素隐藏元素显示", "makeElementDisplay"),
    SCROLLTOTOP("滚动到窗口最上方", "scrollToTop"),
    SCROLLTOBOTTOM("滚动到窗口最下方", "scrollToBottom"),
    SCROLLTOELEMENT("滚动到某个元素", "scrollToElement"),

    //    KeyboardHandleMethod: 处理方法键盘方法
    KEYBOARDOPERATION("All", "keyboardOperation"),

    //    MouseHandleMethod: 处理方法浏览器方法
    MOVETOELEMENTBY("鼠標悬浮", "moveToElementBy"),
    RIGHTCLICKWEBELEMENT("鼠標右键点击", "rightClickWebElement"),
    LEFTCLICKWEBELEMENT("鼠標左键点击", "leftClickWebElement"),
    DOUBLECLICKWEBELEMENT("鼠標双击", "doubleClickWebElement"),
    MOVETOELEMENTANDCLICK("鼠标移动到指定元素,并点击", "moveToElementAndClick"),
    CLICKHOLDANDRELEASE("鼠标点击和释放", "clickHoldAndRelease"),

    //    ElementsHandleMethod: 处理方法浏览器方法
    FINDELEMENTCLICK("单击", "findElementClick"),
    FINDELEMENTCLEAR("清除", "findElementClear"),
    FINDELEMENTCLEARANDSENDKEYS("输入", "findElementClearAndSendKeys"),
    CLICK("点击", "click"),
    WAITFORENABLEDBYXPATHANDCLICK("等待元素可用再点击", "waitForEnabledByXpathAndClick"),

    //FileHandleMethod: 处理方法文件方法
    FLAILED("非input元素上传", "fileUploadByRobot");

    private String methodName;

    private String keyName;

    public String getMethodsNameByKey() {
        return this.methodName;
    }

    public String getKeyName() {
        return this.keyName;
    }

    MethodsNameByKey(String keyName, String methodName) {
        this.keyName = keyName;
        this.methodName = methodName;
    }
}
