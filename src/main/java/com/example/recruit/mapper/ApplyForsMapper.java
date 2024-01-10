package com.example.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.recruit.domain.ApplyFor;
import com.example.recruit.domain.ApplyFors;

import java.util.List;

/**
* @author 32471
* @description 针对表【apply_for(申请记录表)】的数据库操作Mapper
* @createDate 2023-12-26 15:36:00
* @Entity com.example.recruit.domain.ApplyFor
*/
public interface ApplyForsMapper extends BaseMapper<ApplyFors> {
    List<ApplyFor> listAllByPositionId(Integer positionId);
}




