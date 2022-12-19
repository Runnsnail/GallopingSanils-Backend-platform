package com.snail.abell.generator.config;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

/**
 * @author Abell
 * @date 2022/12/12
 */
@Plugin(name = "WebSocketAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class WebSocketAppender extends AbstractAppender {

    /**
     * 一个阻塞队列
     */
    private LoggerQueue loggerQueue = LoggerQueue.getInstance();

    protected WebSocketAppender(String name,
                                Filter filter,
                                Layout<? extends Serializable> layout,
                                boolean ignoreExceptions,
                                Property[] properties) {
        super(name, filter, layout, ignoreExceptions, properties);
    }

    /**
     * TODO：未考虑并发
     * 这个方法就是将日志文件放到哪的具体实现
     * 这里将日志文件转换为字符串后并没有直接给WebSocket而是给一个阻塞队列进行缓冲
     *
     * @param event
     */
    @Override
    public void append(LogEvent event) {
        loggerQueue.push(new String(getLayout().toByteArray(event)));
    }


    /**
     * 用来构造这个类
     *
     * @param name 名称
     * @param ignoreExceptions 异常
     * @param layout 格式
     * @param filter 过滤器
     * @return 日志Appender
     */
    @PluginFactory
    public static WebSocketAppender createAppender(@PluginAttribute("name") String name,
                                                   @PluginAttribute("ignoreExceptions") boolean ignoreExceptions,
                                                   @PluginElement("Layout") Layout layout,
                                                   @PluginElement("Filters") Filter filter) {


        if (name == null) {
            LOGGER.error("No name provided for WebSocketAppender");
            return null;
        }

        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new WebSocketAppender(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
    }
}
