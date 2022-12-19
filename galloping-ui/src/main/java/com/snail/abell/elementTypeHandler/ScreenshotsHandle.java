package com.snail.abell.elementTypeHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Abell
 * @date 2022/11/9
 */
public class ScreenshotsHandle {

    public void screenShot(WebDriver driver) {
        String dirName = "screenshot";
        if (!(new File(dirName).isDirectory())) {
            new File(dirName).mkdir();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = sdf.format(new Date());
        try {
            // 执行截屏
            File sourceFile = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
            FileUtils.copyFile(sourceFile, new File(dirName + File.separator + time + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 截图重命名保存至picture文件夹
     * @throws IOException 抛出异常
     */
    public void takeScreenshotByNow(WebDriver driver) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = sdf.format(new Date());
        String file = "D:\\EasySelenium\\src\\main\\resources\\Picture\\" + time + ".png";
        FileUtils.copyFile(srcFile, new File(file));
    }

    /**
     * 截图重命名保存至picture文件夹
     * @param name 名称
     * @throws IOException  抛出异常
     */
    public void takeScreenshotByName(String name,WebDriver driver) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String file = "D:\\EasySelenium\\src\\main\\resources\\Picture\\" + name + ".png";
        FileUtils.copyFile(srcFile, new File(file));
    }
}
