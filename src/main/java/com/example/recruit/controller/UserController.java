package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.Unit;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.UserService;
import com.example.recruit.service.es.EsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 8:51
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService service;
    private final RabbitTemplate template;
    private final EsService esService;

    @GetMapping("/getId")
    public BaseResponse<User> getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getPhone")
    public BaseResponse<User> getPhone(Integer phone) {
        return success(service.getOne(new QueryWrapper<User>().eq("phone", phone)));
    }

    @PutMapping("/update")
    public BaseResponse update(User user) {
        service.update(user, new UpdateWrapper<User>().eq("userId", user.getUserId()));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
        return success();
    }

    @DeleteMapping("/delete")
    public BaseResponse delete(Integer userId) {
        User user = new User();
        user.setUserState(0);
        service.update(user, new UpdateWrapper<User>().eq("userId", userId));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
        return success();
    }

    @GetMapping("/getList")
    public BaseResponse<List<User>> getList(User user, Integer pageNum, Integer pageSize) {
        List<User> list = new ArrayList<>();
        List<UserDoc> docList = esService.listNamesByNames(UserDoc.class, pageNum, pageSize, BeanUtil.beanToMap(user));
        docList.forEach(userDoc -> list.add(getUser(userDoc)));
        return success(list);
    }

    private UserDoc getUserDoc(User user){
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
        return userDoc;
    }

    private User getUser(UserDoc userDoc) {
        User user = new User();
        user.setUserId(userDoc.getUserId());
        user.setUserGender(userDoc.getUserGender());
        user.setUserAge(userDoc.getUserAge());
        user.setUserName(userDoc.getUserName());
        user.setUserObey(userDoc.getUserObey());
        user.setUserNation(userDoc.getUserNation());
        user.setUserPhone(userDoc.getUserPhone());
        user.setUserStature(userDoc.getUserStature());
        user.setUserState(userDoc.getUserState());
        user.setUserWeight(userDoc.getUserWeight());
        user.setUserPutUp(userDoc.getUserPutUp());
        return user;
    }
}
