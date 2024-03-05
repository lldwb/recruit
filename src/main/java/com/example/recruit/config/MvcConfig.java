package com.example.recruit.config;

import com.example.recruit.controller.interceptor.GetInterceptor;
import com.example.recruit.controller.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 启动mvc注解驱动
@EnableWebMvc
@RequiredArgsConstructor
// 实现 WebMvcConfigurer接口 用于覆盖默认的配置
public class MvcConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final GetInterceptor getInterceptor;

    /**
     * 装配拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(loginInterceptor)
                // 拦截的请求
                .addPathPatterns("/**");
        // 排除的请求
//                .excludePathPatterns("//*.js", "/*/login");
        // 查询拦截器
        registry.addInterceptor(getInterceptor).addPathPatterns("/**");
    }

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("*");
        // 跨域是是否允许传递cookie，默认是不允许的
        //.allowCredentials(true)
    }
}
