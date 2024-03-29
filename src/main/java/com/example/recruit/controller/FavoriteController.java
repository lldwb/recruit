package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.domain.Favorite;
import com.example.recruit.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return success(service.list(new QueryWrapper<Favorite>().allEq(BeanUtil.beanToMap(favorite,true,true))));
    }

    @PutMapping("/add")
    public BaseResponse add(@RequestBody Favorite favorite) {
        service.save(favorite);
        return success();
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Favorite favorite){
        service.update(favorite, new UpdateWrapper<Favorite>().eq("favorite_id", favorite.getFavoriteId()));
        return success();
    }
}
