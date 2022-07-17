package com.snail.abell.apiInterface;

import java.lang.annotation.*;



/**
 * @author Abell
 * 作用于方法和类（接口）上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
