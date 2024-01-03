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
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.ApplyForService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/getId")
    public BaseResponse getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getList")
    public BaseResponse getList(ApplyFor applyFor) {
        return success(service.list(new QueryWrapper<ApplyFor>().allEq(BeanUtil.beanToMap(applyFor,false,true))));
    }

    @PutMapping("/add")
    public BaseResponse add(ApplyFor applyFor) {
        service.save(applyFor);
        return success();
    }

    @PostMapping("/update")
    public BaseResponse update(ApplyFor applyFor){
        service.update(applyFor, new UpdateWrapper<ApplyFor>().eq("applyForId", applyFor.getPositionId()));
        return success();
    }

}
