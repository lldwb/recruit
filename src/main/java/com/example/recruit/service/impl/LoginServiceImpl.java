package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.common.ErrorCode;
import com.example.recruit.domain.User;
import com.example.recruit.exception.BusinessException;
import com.example.recruit.mapper.UserMapper;
import com.example.recruit.service.LoginService;
import com.example.recruit.service.UserService;
import lombok.RequiredArgsConstructor;
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
    private final UserService userService;

    @Override
    public User login(User user, String... args) {
        /// 验证码验证逻辑
        if (true) {
            return userService.getOne(new QueryWrapper<User>().eq("phone", user.getUserPhone()));
        } else {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public User register(User user, String authCode) {
        /// 验证码验证逻辑
        if (true) {
            userService.save(user);
            return userService.getById(user.getUserId());
        } else {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }
}
