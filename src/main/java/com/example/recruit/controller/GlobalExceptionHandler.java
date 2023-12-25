package com.example.recruit.controller;

import com.example.recruit.common.BaseResponse;
import com.example.recruit.common.ErrorCode;
import com.example.recruit.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BaseController{
    // 通过一个注解来定义了,我要针对什么异常,去做什么样的处理
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("businessException" + e.getMessage(), e);
        return error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }
}
