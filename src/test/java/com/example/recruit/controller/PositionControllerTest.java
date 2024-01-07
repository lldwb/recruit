package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.domain.Position;
import com.example.recruit.service.PositionService;
import com.example.recruit.service.es.EsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.recruit.controller.PositionController.getPosition;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/1/3
 * @time 9:54
 * @PROJECT_NAME recruit
 */
@Slf4j
@SpringBootTest
class PositionControllerTest {
    @Autowired
    private PositionService service;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private EsService esService;

    @Test
    void getList() {
        Position position = new Position();
        service.list(new QueryWrapper<Position>().allEq(BeanUtil.beanToMap(position, false, true)));
    }

    @Test
    void getListPlus() {
        Position position = new Position();
        position.setPositionName("演员");
//        position.setPositionAffiliatedUnit("欢乐世界");
        List<Position> list = new ArrayList<>();
//        List<PositionDoc> docList = esService.listNamesByNames(PositionDoc.class, 0, 10, BeanUtil.beanToMap(position, false, true));

        Map<String,Object> map = new HashMap<>();
        map.put("positionName","演员");
        List<PositionDoc> docList = esService.listNamesByNames(PositionDoc.class, 0, 10, "演员","positionName","positionAffiliatedUnit");
        docList.forEach(positionDoc -> list.add(getPosition(positionDoc)));
        log.info("Position：{}",list);
    }
}