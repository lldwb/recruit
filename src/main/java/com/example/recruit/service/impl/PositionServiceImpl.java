package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recruit.domain.Position;
import com.example.recruit.service.PositionService;
import com.example.recruit.mapper.PositionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 32471
 * @description 针对表【position(职位工作表)】的数据库操作Service实现
 * @createDate 2023-12-26 15:06:52
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position>
        implements PositionService {
    private final PositionMapper positionMapper;

    @Override
    public List<Position> list(Map<String, Object> map) {
        log.info("字段：{}",map);
        return positionMapper.selectByMap(map);
    }
}




