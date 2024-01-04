package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.Position;
import com.example.recruit.domain.Unit;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.PositionService;
import com.example.recruit.service.es.EsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

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
        return success(service.list(BeanUtil.beanToMap(position, true, true)));
    }

    /**
     * 根据名字搜索职位，但是只能通过名字搜索，用于模糊搜索
     *
     * @param position
     * @return
     */
    @GetMapping("/getListPlus")
    public BaseResponse getListPlus(PositionDoc position, Integer pageNum, Integer pageSize) {
        List<Position> list = new ArrayList<>();
        List<PositionDoc> docList;
        if (pageNum == null || pageSize == null) {
            docList = esService.listNamesByNames(PositionDoc.class, position.getPositionName(), "positionName");
        } else {
            docList = esService.listNamesByNames(PositionDoc.class, pageNum, pageSize, position.getPositionName(), "positionName");
        }
        docList.forEach(positionDoc -> list.add(getPosition(positionDoc)));
        return success(list);
    }

    @PutMapping("/add")
    public BaseResponse add(Position position) {
        service.save(position);
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getPositionDoc(position)));
        return success();
    }

    @PostMapping("/update")
    public BaseResponse update(Position position) {
        service.update(position, new UpdateWrapper<Position>().eq("position_id", position.getPositionId()));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getPositionDoc(position)));
        return success();
    }

    private PositionDoc getPositionDoc(Position position) {
        PositionDoc positionDoc = new PositionDoc();
        positionDoc.setPositionId(position.getPositionId());
        positionDoc.setPositionName(position.getPositionName());
        positionDoc.setPositionPositionState(position.getPositionPositionState());
        positionDoc.setPositionAffiliatedUnit(position.getPositionAffiliatedUnit());
        positionDoc.setPositionHeat(position.getPositionHeat());
        positionDoc.setPositionSalary(position.getPositionSalary());
//        if ("长期".equals(position.getPositionStartTime())) {
//            positionDoc.setPositionEndTime(position.getPositionEndTime());
//            positionDoc.setPositionStartTime(position.getPositionStartTime());
//        }
        return positionDoc;
    }

    private Position getPosition(PositionDoc positionDoc) {
        Position position = new Position();
        position.setPositionId(positionDoc.getPositionId());
        position.setPositionName(positionDoc.getPositionName());
        position.setPositionPositionState(positionDoc.getPositionPositionState());
        position.setPositionAffiliatedUnit(positionDoc.getPositionAffiliatedUnit());
        position.setPositionHeat(positionDoc.getPositionHeat());
        position.setPositionSalary(positionDoc.getPositionSalary());
//        if (positionDoc.getPositionStartTime() == null && "".equals(positionDoc.getPositionStartTime())) {
//            position.setPositionEndTime("长期");
//        }else {
//            position.setPositionEndTime(positionDoc.getPositionEndTime());
//            position.setPositionStartTime(positionDoc.getPositionStartTime());
//        }
        return position;
    }
}
