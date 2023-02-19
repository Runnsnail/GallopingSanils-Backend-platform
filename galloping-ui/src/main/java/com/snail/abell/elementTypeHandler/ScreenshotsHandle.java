package com.snail.abell.elementTypeHandler;

import com.snail.abell.minio.MinioService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Abell
 * @date 2022/11/9
 */
@Component
public class ScreenshotsHandle {

    @Resource
    private  MinioService minioService;



    public String screenShot(WebDriver driver, String bucketName,String stepName) {

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return minioService.putScreenImage(srcFile, bucketName,stepName );
    }

    /**
     * 截图重命名保存至picture文件夹
     *
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
     *
     * @param name 名称
     * @throws IOException 抛出异常
     */
    public void takeScreenshotByName(String name, WebDriver driver) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String file = "D:\\EasySelenium\\src\\main\\resources\\Picture\\" + name + ".png";
        FileUtils.copyFile(srcFile, new File(file));
    }
}
