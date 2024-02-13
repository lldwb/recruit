package com.example.recruit.mapper;

import com.example.recruit.domain.ApplyFor;
import com.example.recruit.domain.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lldwb
 * @description 针对表【position(职位工作表)】的数据库操作Mapper
 * @createDate 2024-01-06 22:50:51
 * @Entity com.example.recruit.domain.Position
 */
public interface PositionMapper extends BaseMapper<Position> {

    List<Position> listByRegionId(Integer regionId, Integer pageNum, Integer pageSize);
}




