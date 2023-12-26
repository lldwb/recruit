package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.domain.Region;
import com.example.recruit.domain.Unit;
import com.example.recruit.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 10:44
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/unit")
@RequiredArgsConstructor
public class UnitController extends BaseController{
    private final UnitService service;

    @GetMapping("/getId")
    public BaseResponse getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getList")
    public BaseResponse getList(Unit unit) {
        return success(service.list(new QueryWrapper<Unit>().allEq(BeanUtil.beanToMap(unit))));
    }

    @PutMapping("/add")
    public BaseResponse add(Unit unit){
        service.save(unit);
        return success();
    }


}
