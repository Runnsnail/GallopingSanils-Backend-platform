package com.snail.abell.logInterface;

import java.lang.annotation.*;

/**
 * @author Abell
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String description() default "";
    int type() default 0;
}
