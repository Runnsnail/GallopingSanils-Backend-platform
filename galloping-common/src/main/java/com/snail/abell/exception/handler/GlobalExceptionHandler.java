/*
 *  Copyright 
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.snail.abell.exception.handler;

import com.snail.abell.base.ErrorResult;
import com.snail.abell.exception.*;
import com.snail.abell.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Abell
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResult> handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ErrorResult.error(e.getMessage()));
    }

    /**
     * BadCredentialsException
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResult> badCredentialsException(BadCredentialsException e) {
        // 打印堆栈信息
        String message = "坏的凭证".equals(e.getMessage()) ? "用户名或密码不正确" : e.getMessage();
        log.error(message);
        return buildResponseEntity(ErrorResult.error(message));
    }

    /**
     * 处理自定义异常,基本状态码
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResult> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ErrorResult.error(e.getStatus(), e.getMessage()));
    }

    /**
     * 统一处理自定义基础异常
     */
    @ExceptionHandler(BaseException.class)
    public ErrorResult baseException(BaseException e) {
        if (StringUtils.isEmpty(e.getCode())) {
            return ErrorResult.error(e.getMessage());
        }
        return ErrorResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 统一处理自定义业务异常
     */
    @ExceptionHandler(BizException.class)
    public ErrorResult bizException(BizException e) {
        if (StringUtils.isEmpty(e.getCode())) {
            return ErrorResult.error(e.getMessage());
        }
        return ErrorResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理 EntityExist
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ErrorResult> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ErrorResult.error(e.getMessage()));
    }

    /**
     * 处理 EntityNotFound
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResult> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ErrorResult.error(NOT_FOUND.value(), e.getMessage()));
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = "不能为空";
        if (msg.equals(message)) {
            message = str[1] + ":" + message;
        }
        return buildResponseEntity(ErrorResult.error(message));
    }

    /**
     * 统一返回
     */
    private ResponseEntity<ErrorResult> buildResponseEntity(ErrorResult ErrorResult) {
        return new ResponseEntity<>(ErrorResult, HttpStatus.valueOf(ErrorResult.getCode()));
    }
}
