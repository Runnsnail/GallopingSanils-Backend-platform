package com.snail.abell.generator.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Abell
 * @date 2022/12/12
 */
public class LoggerQueue {

    public static final int QUEUE_MAX_SIZE = Integer.MAX_VALUE;
    private static final LoggerQueue alarmMessageQueue = new LoggerQueue();
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    public static LoggerQueue getInstance() {
        return alarmMessageQueue;
    }

    /**
     * 消息入队
     * @param log 日志
     * @return 是否成功
     */
    public boolean push(String log) {
        return this.queue.add(log);
    }

    /**
     * 消息出队
     *
     * @return
     */
    public String pop() {
        String result = null;
        try {
            result = this.queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
