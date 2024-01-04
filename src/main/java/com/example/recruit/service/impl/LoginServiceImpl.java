package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.common.ErrorCode;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.config.RedisConfig;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.exception.BusinessException;
import com.example.recruit.service.LoginService;
import com.example.recruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 8:41
 * @PROJECT_NAME recruit
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserService service;
    private final RabbitTemplate template;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public User login(User user, String... args) {
        String authCode = (String) redisTemplate.opsForValue().get(RedisConfig.REDIS_INDEX + "verification_code:" + user.getUserPhone());

        redisTemplate.delete(RedisConfig.REDIS_INDEX + "verification_code:" + user.getUserPhone());
        /// 验证码验证逻辑
        if (authCode == null || "".equals(authCode) || authCode.equals(args[0])) {
            User users = service.getOne(new QueryWrapper<User>().eq("user_phone", user.getUserPhone()));
            if (users == null) {
                service.save(user);
                template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
                return user;
            } else {
                return users;
            }
        } else {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    private UserDoc getUserDoc(User user) {
        UserDoc userDoc = new UserDoc();
        userDoc.setUserId(user.getUserId());
        userDoc.setUserGender(user.getUserGender());
        userDoc.setUserAge(user.getUserAge());
        userDoc.setUserName(user.getUserName());
        userDoc.setUserObey(user.getUserObey());
        userDoc.setUserNation(user.getUserNation());
        userDoc.setUserPhone(user.getUserPhone());
        userDoc.setUserStature(user.getUserStature());
        userDoc.setUserState(user.getUserState());
        userDoc.setUserWeight(user.getUserWeight());
        userDoc.setUserPutUp(user.getUserPutUp());
        userDoc.setUserHeadPortrait(user.getUserHeadPortrait());
        return userDoc;
    }
}
