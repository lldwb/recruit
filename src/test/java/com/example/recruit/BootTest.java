package com.example.recruit;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.cglib.CglibUtil;
import com.example.recruit.doc.PositionDoc;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.Position;
import com.example.recruit.domain.User;
import com.example.recruit.service.PositionService;
import com.example.recruit.service.UserService;
import com.example.recruit.service.es.EsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

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
    private UserService userService;
    @Autowired
    private EsService esService;
    @Autowired
    private ElasticsearchOperations template;

    @Test
    public void test() {
        esService.deleteIndex(PositionDoc.class);
        esService.deleteIndex(UserDoc.class);

        for (Position position : positionService.list()) {
            template.save(Convert.convert(PositionDoc.class, getPositionDoc(position)));
        }

        for (User user : userService.list()) {
            template.save(Convert.convert(UserDoc.class, getUserDoc(user)));
        }
    }

    private PositionDoc getPositionDoc(Position position){
        log.info("Position：{}",position);
        PositionDoc positionDoc = new PositionDoc();
        positionDoc.setPositionId(position.getPositionId());
        positionDoc.setPositionName(position.getPositionName());
        positionDoc.setPositionPositionState(position.getPositionPositionState());
        positionDoc.setPositionAffiliatedUnit(position.getPositionAffiliatedUnit());
        return positionDoc;
    }

    private UserDoc getUserDoc(User user){
        log.info("User：{}",user);
        UserDoc userDoc = new UserDoc();
        userDoc.setUserId(user.getUserId());
        userDoc.setUserGender(user.getUserGender());
        userDoc.setUserAge(user.getUserAge());
        userDoc.setUserName(user.getUserName());
        userDoc.setUserObey(user.getUserObey());
        userDoc.setUserNation(user.getUserNation());
        userDoc.setUserPhone(user.getUserPhone());
        userDoc.setUserStature(user.getUserStature());
        userDoc.setUserState(user.getUserState());
        userDoc.setUserWeight(user.getUserWeight());
        userDoc.setUserPutUp(user.getUserPutUp());
        return userDoc;
    }
}
