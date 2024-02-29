package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.ECKeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.extra.cglib.CglibUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.config.RedisConfig;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.Position;
import com.example.recruit.domain.Unit;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.mapper.PositionMapper;
import com.example.recruit.service.PositionService;
import com.example.recruit.service.es.EsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    private final PositionMapper mapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitTemplate template;
    private final EsService esService;

    @GetMapping("/getId")
    public BaseResponse getId(Integer id, Integer userId) {
        Position position = service.getById(id);
        if (position != null && position.getPositionId() != null && userId != null && userId > 0) {
            // 查看过的用户
            String positionIdAndUserId = RedisConfig.REDIS_INDEX + "查看过的用户:" + id;
            if (redisTemplate.opsForSet().add(positionIdAndUserId, userId) == 1) {
                // 职位热度
                String jobPopularity = RedisConfig.REDIS_INDEX + "职位热度";
                if (redisTemplate.hasKey(jobPopularity)) {
                    redisTemplate.opsForZSet().incrementScore(jobPopularity, position, -1);
                } else {
                    redisTemplate.opsForZSet().add(jobPopularity, position, 1);
                }
                // 历史记录
                String history = RedisConfig.REDIS_INDEX + "历史记录:" + userId;
                redisTemplate.opsForZSet().add(history, position, System.nanoTime());
            }
        }
        return success(position);
    }

    /**
     * 获取用户历史记录
     *
     * @param userId
     * @return
     */
    @GetMapping("/history")
    public BaseResponse history(Integer userId) {
//        redisTemplate.opsForZSet().("",10);
        return success();
    }

    /**
     * 热度榜单
     *
     * @return
     */
    @GetMapping("heat")
    public BaseResponse heat() {
        // 职位热度
        String jobPopularity = RedisConfig.REDIS_INDEX + "职位热度";
        return success(redisTemplate.opsForZSet().range(jobPopularity, 0, 10));
    }

    @GetMapping("/getList")
    public BaseResponse getList(Position position, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 0) {
            pageNum = 0;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        IPage page = new Page(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper<Position>().allEq(BeanUtil.beanToMap(position, true, true));
        mapper.selectPage(page, queryWrapper);
        page.getPages();
        return success(page);

//        return success(service.list(new QueryWrapper<Position>().allEq(BeanUtil.beanToMap(position, true, true))));
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

    /**
     * 使用地区id返回职位列表
     *
     * @param regionId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/listByRegionId")
    public BaseResponse listByRegionId(Integer regionId, Integer pageNum, Integer pageSize) {
        List<Position> list = mapper.listByRegionId(regionId, pageNum, pageSize);
        return success(list);
    }

    @PutMapping("/add")
    public BaseResponse add(@RequestBody Position position) {
        service.save(position);
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getPositionDoc(position)));
        return success();
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody Position position) {
        service.update(position, new UpdateWrapper<Position>().eq("position_id", position.getPositionId()));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getPositionDoc(position)));
        return success();
    }

    public static PositionDoc getPositionDoc(Position position) {
        ObjectMapper objectMapper = new ObjectMapper();
        PositionDoc positionDoc = new PositionDoc();
        positionDoc.setPositionId(position.getPositionId());
        positionDoc.setPositionName(position.getPositionName());
        positionDoc.setPositionNumber(position.getPositionNumber());
        positionDoc.setPositionAffiliatedUnit(position.getPositionAffiliatedUnit());
        positionDoc.setPositionHeat(position.getPositionHeat());
        positionDoc.setPositionSalary(position.getPositionSalary());
        if (position.getPositionStartTime() != null && !"".equals(position.getPositionStartTime()) && !"长期".equals(position.getPositionStartTime())) {
            try {
                positionDoc.setPositionEndTime(objectMapper.writeValueAsString(position.getPositionEndTime().toCharArray()));
                positionDoc.setPositionStartTime(objectMapper.writeValueAsString(position.getPositionStartTime().toCharArray()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            positionDoc.setPositionEndTime("长期");
            positionDoc.setPositionStartTime("长期");
        }
        return positionDoc;
    }

    public static Position getPosition(PositionDoc positionDoc) {
        ObjectMapper objectMapper = new ObjectMapper();
        Position position = new Position();
        position.setPositionId(positionDoc.getPositionId());
        position.setPositionName(positionDoc.getPositionName());
        position.setPositionNumber(positionDoc.getPositionNumber());
        position.setPositionAffiliatedUnit(positionDoc.getPositionAffiliatedUnit());
        position.setPositionHeat(positionDoc.getPositionHeat());
        position.setPositionSalary(positionDoc.getPositionSalary());
        if ("".equals(positionDoc.getPositionStartTime()) || "长期".equals(positionDoc.getPositionStartTime())) {
            position.setPositionEndTime("长期");
            position.setPositionStartTime("长期");
        } else {
            position.setPositionEndTime(new String(objectMapper.convertValue(positionDoc.getPositionEndTime(), char[].class)));
            position.setPositionStartTime(new String(objectMapper.convertValue(positionDoc.getPositionStartTime(), char[].class)));
        }
        return position;
    }
}
