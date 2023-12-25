package com.example.recruit.contant;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.domain.User;
import com.example.recruit.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    private final LoginService service;

    @PostMapping("/login")
    public BaseResponse<String> login(User user, String authCode) {
        user = service.login(user, authCode);
        String jwt = JWT.create()
                // 设置签发时间
                .setIssuedAt(DateUtil.date())
                // 设置过期时间
                .setExpiresAt(DateUtil.date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
                // 设置用户id
                .setKey(user.getUserId().toString().getBytes())
                // 签名生成JWT字符串
                .sign();
        return success(jwt);
    }
    @PostMapping("/register")
    public BaseResponse<String> register(User user, String authCode) {
        user = service.register(user, authCode);
        String jwt = JWT.create()
                // 设置签发时间
                .setIssuedAt(DateUtil.date())
                // 设置过期时间
                .setExpiresAt(DateUtil.date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
                // 设置用户id
                .setKey(user.getUserId().toString().getBytes())
                // 签名生成JWT字符串
                .sign();
        return success(jwt);
    }
}
