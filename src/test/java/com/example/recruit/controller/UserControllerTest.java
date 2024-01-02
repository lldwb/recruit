package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.User;
import com.example.recruit.service.UserService;
import com.example.recruit.service.es.EsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 16:36
 * @PROJECT_NAME recruit
 */
@Slf4j
@SpringBootTest
class UserControllerTest {
   @Autowired
    private UserService service;
    @Autowired
    private EsService esService;
    @Test
    void getId() {
//        log.info(String.valueOf(service.getOne(new QueryWrapper<User>().eq("user_id", 1))));
        log.info(String.valueOf(service.getById(1)));
    }

    @Test
    void getList() {
        User user = new User();
        List<UserDoc> docList = esService.listNamesByNames(UserDoc.class, 0, 10, BeanUtil.beanToMap(user));
        docList.forEach(userDoc -> log.info("用户：{}",userDoc));
    }
}