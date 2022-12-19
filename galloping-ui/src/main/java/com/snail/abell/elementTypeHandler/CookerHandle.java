package com.snail.abell.elementTypeHandler;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Abell
 * @date 2022/11/9
 */
public class CookerHandle {

    /**
     *     获取当前域所有的cookies
     * @return Set<Cookie> 当前的cookies集合
     */
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    /**
     * 输出cookies信息
     * @param driver 驱动
     */
    public void outputCookie(WebDriver driver) {
        Set<Cookie> cookie = driver.manage().getCookies();
        System.out.println(cookie);
    }

    /**
     * 添加cookie信息
     * @param args cooker信息
     * @param driver 驱动
     */
    public void addCookie(Map<String, String> args,WebDriver driver) {
        Set<String> keys = args.keySet();
        for (String key : keys) {
            driver.manage().addCookie(new Cookie(key, args.get(key)));
        }
    }
    /**
     *     用给定的name和value创建默认路径的Cookie并添加, 永久有效
     * @param name cooker名称
     * @param value cooker值
     */
    public static void addCookie(String name, String value,WebDriver driver) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    /**
     *     用给定的name和value创建指定路径的Cookie并添加, 永久有效
     * @param name  cookie名称
     * @param value cookie值
     * @param path  cookie路径
     */
    public static void addCookie(String name, String value, String path,WebDriver driver) {
        driver.manage().addCookie(new Cookie(name, value, path));
    }
    /**
     *     根据cookie名称删除cookie
     * @param name cookie的name值
     */
    public static void deleteCookie(String name,WebDriver driver) {
        driver.manage().deleteCookieNamed(name);
    }
    /**
     *     删除当前域的所有Cookie
     */
    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    /**
     *     根据名称获取指定cookie
     * @param name cookie名称
     * @return Map<String, String>, 如果没有cookie则返回空, 返回的Map的key值如下:
     *         <ul>
     *         <li><tt>name</tt> <tt>cookie名称</tt>
     *         <li><tt>value</tt> <tt>cookie值</tt>
     *         <li><tt>path</tt> <tt>cookie路径</tt>
     *         <li><tt>domain</tt> <tt>cookie域</tt>
     *         <li><tt>expiry</tt> <tt>cookie有效期</tt>
     *         </ul>
     */
    public static Map<String, String> getCookieByName(String name,WebDriver driver) {
        Cookie cookie = driver.manage().getCookieNamed(name);
        if (cookie != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", cookie.getName());
            map.put("value", cookie.getValue());
            map.put("path", cookie.getPath());
            map.put("domain", cookie.getDomain());
            map.put("expiry", cookie.getExpiry().toString());
            return map;
        }
        return null;
    }
}
