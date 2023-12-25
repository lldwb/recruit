package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recruit.domain.Favorite;
import com.example.recruit.service.FavoriteService;
import com.example.recruit.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author lldwb
* @description 针对表【favorite】的数据库操作Service实现
* @createDate 2023-12-24 21:22:40
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




