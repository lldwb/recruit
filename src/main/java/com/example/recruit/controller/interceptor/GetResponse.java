package com.example.recruit.controller.interceptor;

import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RedisConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.Duration;


/**
 * 查询返回值拦截器
 *
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/3/1
 * @time 13:34
 * @PROJECT_NAME recruit
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GetResponse implements ResponseBodyAdvice<Object> {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        String method = serverHttpRequest.getMethod().toString();
        String URL = RedisConfig.REDIS_INDEX + "URL:" + method + ":" + serverHttpRequest.getURI().getRawPath() + "?" + serverHttpRequest.getURI().getQuery();
        
//        log.info(URL);
//        log.info(body.toString());

        if ("GET".equals(method)) {
            log.info("完整查询地址：" + URL);
            BaseResponse baseResponse = (BaseResponse) body;
            redisTemplate.opsForValue().set(URL, baseResponse, Duration.ofSeconds(300));
        }

        return body;
    }
}
