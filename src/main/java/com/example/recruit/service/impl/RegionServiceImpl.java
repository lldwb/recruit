package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recruit.domain.Region;
import com.example.recruit.service.RegionService;
import com.example.recruit.mapper.RegionMapper;
import org.springframework.stereotype.Service;

/**
* @author lldwb
* @description 针对表【region(地区表)】的数据库操作Service实现
* @createDate 2023-12-24 21:22:30
*/
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>
    implements RegionService{

}




