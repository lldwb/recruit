package com.example.recruit.controller.interceptor;

import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RedisConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 查询拦截器(用于解决削峰的问题)
 *
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/3/1
 * @time 10:56
 * @PROJECT_NAME recruit
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GetInterceptor implements HandlerInterceptor {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("方法：" + request.getMethod());
//        log.info("URI：" + request.getRequestURI());
//        log.info("查询字符串：：" + request.getQueryString());
        String URL = RedisConfig.REDIS_INDEX + "URL:" + request.getMethod() + ":" + request.getRequestURI() + "?" + request.getQueryString();
        if ("GET".equals(request.getMethod())) {
            Object data = redisTemplate.opsForValue().get(URL);
//            log.info("完整查询地址：" + URL);
            if (data != null) {
                BaseResponse baseResponse = (BaseResponse) data;
                response.setContentType("application/json");
                response.getWriter().println(new ObjectMapper().writeValueAsString(baseResponse));
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
