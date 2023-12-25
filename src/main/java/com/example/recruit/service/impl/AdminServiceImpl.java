package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recruit.domain.Admin;
import com.example.recruit.service.AdminService;
import com.example.recruit.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author lldwb
* @description 针对表【admin(管理员表)】的数据库操作Service实现
* @createDate 2023-12-24 21:22:46
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




