package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.domain.Favorite;
import com.example.recruit.domain.Position;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 15:53
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController extends BaseController{
    private final FavoriteService service;

    @GetMapping("/getId")
    public BaseResponse getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getList")
    public BaseResponse getList(Favorite favorite) {
        return success(service.list(new QueryWrapper<Favorite>().allEq(BeanUtil.beanToMap(favorite))));
    }

    @PutMapping("/add")
    public BaseResponse add(Favorite favorite) {
        service.save(favorite);
        return success();
    }

    @PostMapping("/update")
    public BaseResponse update(Favorite favorite){
        service.update(favorite, new UpdateWrapper<Favorite>().eq("favoriteId", favorite.getFavoriteId()));
        return success();
    }
}
