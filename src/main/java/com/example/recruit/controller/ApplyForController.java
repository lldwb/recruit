package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.domain.ApplyFor;
import com.example.recruit.domain.Position;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.ApplyForService;
import com.example.recruit.service.PositionService;
import com.example.recruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.recruit.controller.PositionController.getPositionDoc;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 15:36
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/applyFor")
@RequiredArgsConstructor
public class ApplyForController extends BaseController {
    private final ApplyForService service;
    private final UserService userService;
    private final RabbitTemplate template;
    private final PositionService positionService;

    @GetMapping("/getId")
    public BaseResponse getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getList")
    public BaseResponse getList(ApplyFor applyFor) {
        return success(service.list(new QueryWrapper<ApplyFor>().allEq(BeanUtil.beanToMap(applyFor, true, true))));
    }

    @PutMapping("/add")
    public BaseResponse add(@RequestBody ApplyFor applyFor) {
        service.save(applyFor);
        applyFor.setApplyForState("未通过");
        return success(applyFor);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody ApplyFor applyFor) {
        service.update(applyFor, new UpdateWrapper<ApplyFor>().eq("apply_for_id", applyFor.getApplyForId()));
        if ("2".equals(applyFor.getApplyForState())) {
            Position position = new Position();
            position.setPositionId(applyFor.getPositionId());
            position.setPositionNumber(positionService.getById(position.getPositionId()).getPositionNumber() - 1);
            positionService.update(position, new UpdateWrapper<Position>().eq("position_id", position.getPositionId()));
            template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getPositionDoc(position)));
        }
        return success();
    }

    @PostMapping("/consent")
    public BaseResponse consent(@RequestBody ApplyFor applyFor) {
        applyFor.setApplyForState("1");
        service.update(applyFor, new UpdateWrapper<ApplyFor>().eq("apply_for_id", applyFor.getApplyForId()));
        applyFor = service.getById(applyFor.getApplyForId());
        User user = new User();
        user.setUserId(applyFor.getUserId());
        user.setUserState(2);
        userService.update(user, new UpdateWrapper<User>().eq("user_id", user.getUserId()));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(UserController.getUserDoc(user)));
        return success();
    }

}
