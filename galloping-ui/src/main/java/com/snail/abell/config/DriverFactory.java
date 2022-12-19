package com.snail.abell.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Abell
 * @date 2022/12/11
 */
public class DriverFactory {

    private static String gridUrl ="http://localhost:4444/wd/hub";
    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();

    public static synchronized void initDriver(String browser){

        DesiredCapabilities BrowserGrid = null;
        switch (browser) {
            case "Chrome":
                BrowserGrid =DesiredCapabilities.chrome();
                break;
            case "Firefix":
                BrowserGrid =DesiredCapabilities.firefox();
                break;
            case "Edge":
                BrowserGrid =DesiredCapabilities.edge();
                break;
            default:
                try {
                    throw new Exception("浏览器错误!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        try {
            drivers.set(new RemoteWebDriver(new URL(gridUrl),BrowserGrid));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized WebDriver getDriver() {
        return drivers.get();
    }
}
