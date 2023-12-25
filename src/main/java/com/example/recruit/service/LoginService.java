package com.example.recruit.service;

import com.example.recruit.domain.User;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 8:41
 * @PROJECT_NAME recruit
 */
public interface LoginService {
    /**
     * 登录逻辑
     * @param user 用于登录的用户信息
     * @param args 条件
     * @return
     */
    User login(User user, String... args);

    User register(User user, String authCode);
}
