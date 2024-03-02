package com.example.recruit.service.consumer;

import com.example.recruit.controller.LoginController;
import com.example.recruit.domain.User;
import com.example.recruit.dto.AuthCode;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/3/1
 * @time 9:47
 * @PROJECT_NAME recruit
 */
@SpringBootTest
class ConsumerAuthCodeTest {
    @Autowired
    private ConsumerAuthCode consumerAuthCode;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private LoginController loginController;

    @Test
    void sendEmailAuthCode() throws AuthException {
        AuthCode code = new AuthCode();
        code.setSubject("自己");
        code.setReceivingUser("17870143604");
        User user = new User();
        user.setUserPhone(17870143604L);
        // 接口调用
        loginController.sendAuthCode(user);
        // 消息队列调用
//        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitAuthCode.ROUTING_KEY, code);
        // 直接调用
//        consumerAuthCode.sendEmailAuthCode(code);
    }
}