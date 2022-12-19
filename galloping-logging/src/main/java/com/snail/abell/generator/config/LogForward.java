package com.snail.abell.generator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author Abell
 * @date 2022/12/12
 */
@Component
public class LogForward {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private ThreadPoolExecutor threadPoolExecutor;


    private final LoggerQueue loggerQueue = LoggerQueue.getInstance();


//    @Bean
//    public void pushLogs() {
//        threadPoolExecutor.execute(() -> {
//            // 从队列中读取日志并发送
//            while (true) {
//                String message = loggerQueue.pop();
//                messagingTemplate.convertAndSend("/topic/log", message);
//            }
//        });
//    }
}
