package com.example.recruit.config;

import com.example.recruit.config.factory.MinioFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MainIo配置类
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/11/30
 * @time 16:25
 * @PROJECT_NAME file_saving_tool_backend
 */
@Configuration
//才会初始化当前的配置类
//@ConditionalOnClass(MinioClient.class)
public class MinIOConfig {
   // 定义url
    private final String url = "http://www:9000";
    // 定义用户名
    private final String name = "lldwb";
    // 定义密码
    private final String pass = "98b058becd731";
    // 定义桶名
    public static final String BUCKET = "recruit";

    @Bean
    public MinioFactoryBean minioFactoryBean() {
        MinioFactoryBean factoryBean = new MinioFactoryBean();
        factoryBean.setEndpoint(url);
        factoryBean.setUsername(name);
        factoryBean.setPassword(pass);
        factoryBean.setBucket(BUCKET);
        return factoryBean;
    }
}
