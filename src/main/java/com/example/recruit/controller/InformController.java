package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.domain.Inform;
import com.example.recruit.domain.Unit;
import com.example.recruit.service.InformService;
import com.example.recruit.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 16:03
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/inform")
@RequiredArgsConstructor
public class InformController extends BaseController {
    private final InformService service;

    @GetMapping("/getId")
    public BaseResponse getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getList")
    public BaseResponse getList(Inform inform) {
        return success(service.list(new QueryWrapper<Inform>().allEq(BeanUtil.beanToMap(inform,true,true))));
    }

    @PutMapping("/add")
    public BaseResponse add(Inform inform){
        service.save(inform);
        return success();
    }
}
