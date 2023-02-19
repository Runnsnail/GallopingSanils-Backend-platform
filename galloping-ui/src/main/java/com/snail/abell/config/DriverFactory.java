package com.snail.abell.config;

import org.openqa.selenium.Platform;
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

    private static String gridUrl ="http://116.62.219.238:4444/wd/hub";
    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();

    public static synchronized void initDriver(String browser){

        DesiredCapabilities browserGrid = null;
        switch (browser) {
            case "Chrome":
                browserGrid =DesiredCapabilities.chrome();
                browserGrid.setBrowserName("chrome");
                browserGrid.setPlatform(Platform.LINUX);
                browserGrid.setVersion("98.0");
                break;
            case "Firefix":
                browserGrid =DesiredCapabilities.firefox();
                browserGrid.setBrowserName("firefox");
                browserGrid.setPlatform(Platform.LINUX);
                browserGrid.setVersion("96.0");
                break;
            case "Edge":
                browserGrid =DesiredCapabilities.edge();
                browserGrid.setBrowserName("edge");
                browserGrid.setPlatform(Platform.LINUX);
                browserGrid.setVersion("98.0");
                break;
            default:
                try {
                    browserGrid =DesiredCapabilities.chrome();
                    browserGrid.setBrowserName("chrome");
                    browserGrid.setPlatform(Platform.LINUX);
                    browserGrid.setVersion("98.0");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        try {
            drivers.set(new RemoteWebDriver(new URL(gridUrl),browserGrid));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized WebDriver getDriver() {
        return drivers.get();
    }
}
