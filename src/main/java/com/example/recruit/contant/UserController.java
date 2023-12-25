package com.example.recruit.contant;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.UserService;
import com.example.recruit.service.es.EsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.redis.core.RedisTemplate;
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

    @GetMapping("/getUserById")
    public BaseResponse<User> getUserById(Integer userId) {
        return success(service.getById(userId));
    }

    @GetMapping("/getUserByPhone")
    public BaseResponse<User> getUserByPhone(Integer phone) {
        return success(service.getOne(new QueryWrapper<User>().eq("phone", phone)));
    }

    @PutMapping("/updateUser")
    public BaseResponse updateUser(User user) {
        service.update(user, new UpdateWrapper<User>().eq("userId", user.getUserId()));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
        return success();
    }

    @DeleteMapping("/deleteUser")
    public BaseResponse deleteUser(Integer userId) {
        User user = new User();
        user.setUserState(0);
        service.update(user, new UpdateWrapper<User>().eq("userId", userId));
        return success();
    }

    @GetMapping("/getUsers")
    public BaseResponse<List<User>> getUsers(User user, Integer pageNum, Integer pageSize) {
        List<User> list = new ArrayList<>();
        List<UserDoc> docList = esService.listNamesByNames(UserDoc.class, pageNum, pageSize, BeanUtil.beanToMap(user));
        docList.forEach(userDoc -> list.add(getUser(userDoc)));
        return success(list);
    }

    private UserDoc getUserDoc(User user) {
        UserDoc userDoc = new UserDoc();
        CglibUtil.copy(user, userDoc);
        return userDoc;
    }

    private User getUser(UserDoc userDoc) {
        User user = new User();
        CglibUtil.copy(userDoc, user);
        return user;
    }
}
