package com.example.recruit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.recruit.domain.Position;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 32471
* @description 针对表【position(职位工作表)】的数据库操作Service
* @createDate 2023-12-26 15:06:52
*/
public interface PositionService extends IService<Position> {

    List<Position> list(Map<String,Object> map);
}
