package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recruit.domain.Position;
import com.example.recruit.service.PositionService;
import com.example.recruit.mapper.PositionMapper;
import org.springframework.stereotype.Service;

/**
* @author lldwb
* @description 针对表【position(职位工作表)】的数据库操作Service实现
* @createDate 2024-01-06 22:50:51
*/
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position>
    implements PositionService{

}




