package com.snail.abell.config;

import com.snail.abell.elementTypeHandler.SelectorType;

import java.lang.annotation.*;

/**
 * @author Abell
 * @date 2022/11/9
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SelectorTypeAnnotation {

    SelectorType selectorType();
}
