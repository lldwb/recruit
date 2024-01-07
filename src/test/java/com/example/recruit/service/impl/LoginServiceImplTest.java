package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Test
    void login() {
        User user = new User();
        user.setUserPhone(17870143604L);
//        String userPhone = "17870143604";

        User users = service.getOne(new QueryWrapper<User>().eq("user_phone", user.getUserPhone()));
//        User users = service.getOne(new QueryWrapper<User>().eq("user_phone", userPhone));
        // 判断是否注册
        if (users == null) {
            log.info("无数据");
        }else {
            log.info("有数据");
        }
        log.info("user：{}",user);
//        service.save(user);
//        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
    }
}