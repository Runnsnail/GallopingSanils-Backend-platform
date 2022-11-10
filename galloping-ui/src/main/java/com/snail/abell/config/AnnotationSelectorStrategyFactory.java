package com.snail.abell.config;

import com.snail.abell.elementTypeHandler.SelectorType;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Abell
 * @date 2022/11/9
 */
public class AnnotationSelectorStrategyFactory {

    /**
     * 存储策略
     */
    static Map<SelectorType, SelectorStrategy> selectorStrategyMap = new HashMap<>();

    static {
        registerSelectorStrategy();
    }

    /**
     * 通过map获取税策略，当增加新的税策略时无需修改代码，对修改封闭，对扩展开放，遵循开闭原则
     * @param selectorType  选择类型
     * @return 返回策略
     * @throws Exception 抛出异常
     */
    public static SelectorStrategy getSelectorStrategy(SelectorType selectorType) throws Exception {
        // 当增加新的税类型时，需要修改代码，同时增加圈复杂度
        if (selectorStrategyMap.containsKey(selectorType)) {
            return selectorStrategyMap.get(selectorType);
        } else {
            throw new Exception("The Selector type is not supported.");
        }
    }

    /**
     * 自动注册策略
     */
    private static void registerSelectorStrategy() {
        try {
            // 通过反射找到所有的策略子类进行注册
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage(SelectorStrategy.class.getPackage().getName()))
                    .setScanners(new SubTypesScanner()));
            Set<Class<? extends SelectorStrategy>> taxStrategyClassSet = reflections.getSubTypesOf(SelectorStrategy.class);

            if (taxStrategyClassSet != null) {
                for (Class<?> clazz: taxStrategyClassSet) {
                    // 找到税类型注解，自动完成税策略注册
                    if (clazz.isAnnotationPresent(SelectorTypeAnnotation.class)) {
                        SelectorTypeAnnotation taxTypeAnnotation = clazz.getAnnotation(SelectorTypeAnnotation.class);
                        SelectorType selectorType = taxTypeAnnotation.selectorType();
                        selectorStrategyMap.put(selectorType, (SelectorStrategy)clazz.newInstance());
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            // 自行定义异常处理
            e.printStackTrace();
        }
    }

}
