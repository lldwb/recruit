package com.example.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recruit.domain.Favorite;
import com.example.recruit.service.FavoriteService;
import com.example.recruit.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author 32471
* @description 针对表【favorite】的数据库操作Service实现
* @createDate 2023-12-26 15:57:32
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




