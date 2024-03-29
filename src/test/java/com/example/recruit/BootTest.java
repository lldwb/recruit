package com.example.recruit;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.Position;
import com.example.recruit.domain.User;
import com.example.recruit.mapper.PositionMapper;
import com.example.recruit.service.PositionService;
import com.example.recruit.service.UserService;
import com.example.recruit.service.es.EsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import static com.example.recruit.controller.PositionController.getPositionDoc;
import static com.example.recruit.controller.UserController.getUserDoc;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/1/2
 * @time 11:43
 * @PROJECT_NAME recruit
 */
@Slf4j
@SpringBootTest
public class BootTest {
    @Autowired
    private PositionService positionService;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private EsService esService;
    @Autowired
    private ElasticsearchOperations template;

    @Test
    public void test() {
        esService.deleteIndex(PositionDoc.class);

        for (Position position : positionService.list()) {
            log.info("position：{}",getPositionDoc(position));
            template.save(Convert.convert(PositionDoc.class, getPositionDoc(position)));
        }

        esService.deleteIndex(UserDoc.class);
        for (User user : userService.list()) {
            log.info("position：{}",getUserDoc(user));
            template.save(Convert.convert(UserDoc.class, getUserDoc(user)));
        }
    }

    @Test
    public void textMessage() {
        String jwt = JWT.create()
                // 设置签发时间
                .setIssuedAt(DateUtil.date())
                // 设置过期时间
                .setExpiresAt(DateUtil.date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
                .setPayload("userId", 1)
                // 签名生成JWT字符串
                .sign();
        log.info("jwt：{}",jwt);
    }
}
