package com.snail.abell.utils;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Abell
 * @date 2023/3/14
 */
public class DateUtils {


    /**
     * 验证cron表达式是否正确
     * @param cronExpression
     * @return
     */
    public static boolean isValid(String cronExpression){
        return CronExpression.isValidExpression(cronExpression);
    }

    /**
     * 将日期转换为cron时间
     *
     * @param date
     * @return
     */
    public static String parseCron(Date date) {
        String format="ss mm HH dd MM ? yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 时间转字符串
     * @param date
     * @return
     */
    public static String parseDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateFormat = sdf.format(date);
        return dateFormat;
    }

    /**
     * 字符串转时间
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date toDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateFormatParse = sdf.parse(dateString);
        return dateFormatParse;
    }

    public static String parseCron(String dateString) throws ParseException {

        Date dateFormatParse =  toDate(dateString);
        String cornTime = parseCron(dateFormatParse);
        return cornTime;
    }
}
