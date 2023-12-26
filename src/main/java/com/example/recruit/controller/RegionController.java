package com.example.recruit.controller;

import com.example.recruit.common.BaseResponse;
import com.example.recruit.domain.Region;
import com.example.recruit.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 9:13
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/region")
@RequiredArgsConstructor
public class RegionController extends BaseController {
    private final RegionService service;

    @GetMapping("/getId")
    public BaseResponse getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getList")
    public BaseResponse<List<Region>> getList() {
         return success(service.list());
    }

    @PostMapping("/add")
    public BaseResponse add(Region region){
        service.save(region);
        return success();
    }
}
