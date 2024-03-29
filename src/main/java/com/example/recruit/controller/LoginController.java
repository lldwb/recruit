package com.example.recruit.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitAuthCode;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RedisConfig;
import com.example.recruit.domain.User;
import com.example.recruit.dto.AuthCode;
import com.example.recruit.service.LoginService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 8:37
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController extends BaseController {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitTemplate template;
    private final LoginService service;

    /**
     * 发送验证码
     *
     * @param user 用户对象
     * @return 成功响应
     */
    @PostMapping("/sendAuthCode")
    public BaseResponse sendAuthCode(User user) throws AuthException {
        String authCode = (String) redisTemplate.opsForValue().get(RedisConfig.REDIS_INDEX + "verification_code:" + user.getUserPhone());
        if (user == null || user.getUserPhone() == null || "".equals(user.getUserPhone())) {
            throw new AuthException("手机号不能为空");
        }// 防止重复验证码
        else if (authCode != null && !"".equals(authCode)) {
            throw new AuthException("重复验证码");
        }
        AuthCode code = new AuthCode();
        code.setReceivingUser(String.valueOf(user.getUserPhone()));

        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitAuthCode.ROUTING_KEY, code);

        // 验证码过期
        redisTemplate.opsForValue().set(RedisConfig.REDIS_INDEX + "verification_code:" + code.getReceivingUser(), code.getAuthCode(), Duration.ofSeconds(300));
        // 验证码不过期(测试)
//        redisTemplate.opsForValue().set(RedisConfig.REDIS_INDEX + "verification_code:" + code.getReceivingUser(), code.getAuthCode());
        return success();
    }

    @PostMapping("/login")
    public BaseResponse login(User user, String authCode) {
        user = service.login(user, authCode);
        // 用户秘钥加密(开发阶段关闭)
        if (false) {
            String jwt = JWT.create()
                    // 设置签发时间
                    .setIssuedAt(DateUtil.date())
                    // 设置过期时间
                    .setExpiresAt(DateUtil.date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
                    .setPayload("userId", user.getUserId())
                    // 签名生成JWT字符串
                    .sign();
            return success(jwt);
        }
        return success(user.getUserId());
    }
}
