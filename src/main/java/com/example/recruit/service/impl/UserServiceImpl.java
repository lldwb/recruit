package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recruit.domain.User;
import com.example.recruit.service.UserService;
import com.example.recruit.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lldwb
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-01-08 23:55:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




