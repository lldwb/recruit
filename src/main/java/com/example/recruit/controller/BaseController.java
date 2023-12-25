package com.example.recruit.controller;

import com.example.recruit.common.BaseResponse;
import com.example.recruit.common.ErrorCode;

/**
 * 它是所有的用户定义Servlet的父类 - 把一些公共操作的代码，抽象提取出来，在父类中定义，子类可以直接使用
 *
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 8:33
 * @PROJECT_NAME recruit
 */
public abstract class BaseController {
    /**
     * 成功
     *
     * @param <T>
     * @return
     */
    <T> BaseResponse<T> success() {
        return new BaseResponse<>(200, null, "ok");
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param <T>
     * @return
     */
    <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param description
     * @param <T>
     * @return
     */
    <T> BaseResponse<T> error(ErrorCode errorCode, String description) {
        return new BaseResponse(errorCode.getCode(), errorCode.getMessage(), description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param message
     * @param description
     * @param <T>
     * @return
     */
    <T> BaseResponse<T> error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), message, description);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param description
     * @param <T>
     * @return
     */
    <T> BaseResponse<T> error(int code, String message, String description) {
        return new BaseResponse(code, message, description);
    }
}