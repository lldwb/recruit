package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.config.RedisConfig;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

import static com.example.recruit.controller.UserController.getUserDoc;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/1/5
 * @time 8:24
 * @PROJECT_NAME recruit
 */
@Slf4j
@SpringBootTest
class LoginServiceImplTest {
    @Autowired
    private UserService service;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    void login() {

        redisTemplate.opsForValue().set(RedisConfig.REDIS_INDEX + "verification_code:" + "123", "123", Duration.ofSeconds(300));
        String authCode = (String) redisTemplate.opsForValue().get(RedisConfig.REDIS_INDEX + "verification_code:" + "123");
        log.info(authCode);
        redisTemplate.delete(RedisConfig.REDIS_INDEX + "verification_code:" + "123");
    }
}