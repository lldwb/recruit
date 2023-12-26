package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.Position;
import com.example.recruit.domain.Unit;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.PositionService;
import com.example.recruit.service.es.EsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 15:07
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/position")
@RequiredArgsConstructor
public class PositionController extends BaseController {
    private final PositionService service;
    private final RabbitTemplate template;
    private final EsService esService;

    @GetMapping("/getId")
    public BaseResponse getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getList")
    public BaseResponse getList(Position position) {
        return success(service.list(new QueryWrapper<Position>().allEq(BeanUtil.beanToMap(position))));
    }

    /**
     * 根据名字、状态、单位搜索职位，但是只能通过名字、状态、单位搜索，用于模糊搜索
     *
     * @param condition
     * @return
     */
    @GetMapping("/getListPlus")
    public BaseResponse getListPlus(String condition, Integer pageNum, Integer pageSize) {
        List<Position> list = new ArrayList<>();
        List<PositionDoc> docList = esService.listNamesByNames(PositionDoc.class, pageNum, pageSize, condition, "positionName", "positionAffiliatedUnit");
        docList.forEach(positionDoc -> list.add(getPosition(positionDoc)));
        return success(list);
    }

    @PutMapping("/add")
    public BaseResponse add(Position position) {
        service.save(position);
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getPositionDoc(position)));
        return success();
    }

    private PositionDoc getPositionDoc(Position position) {
        PositionDoc positionDoc = new PositionDoc();
        CglibUtil.copy(position, positionDoc);
        return positionDoc;
    }

    private Position getPosition(PositionDoc positionDoc) {
        Position position = new Position();
        CglibUtil.copy(positionDoc, position);
        return position;
    }
}
