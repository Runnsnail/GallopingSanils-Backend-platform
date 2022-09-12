package com.snail.abell.utils;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Abell
 * @date 2022/9/11
 * Description: 流水号工具类
 */
@Component
public class SerialUtil {



        /**
         * 流水号抬头
         */
        private final static String STR = "LSH";
        /**
         * 流水号格式
         */
        private final static String FORMAT_CODE = "0000";


        /**
         * 生成流水号
         * 实际业务需要检查数据库是否存在
         * @return {@link String}
         */
        public static String nextValue() {

            LocalDateTime now = LocalDateTime.now();
            // 格式在这里定义
            String pattern = "yyyyMMdd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            String date = formatter.format(now);
            String count = RandomUtil.randomNumbers(5);;
            // 最终结果
            String result = STR + date + count;
            return result;
        }

}
