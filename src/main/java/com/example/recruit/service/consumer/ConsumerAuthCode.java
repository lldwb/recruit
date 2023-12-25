package com.example.recruit.service.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerAuthCode {
//    private final SendService emailSend;
//    @RabbitListener(queues = RabbitEmailAuthCode.QUEUE_NAME)
//    public void sendEmailAuthCode(AuthCode authCode){
//        emailSend.send(authCode);
//    }
}
