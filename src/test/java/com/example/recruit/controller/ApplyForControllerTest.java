package com.example.recruit.controller;

import com.example.recruit.domain.ApplyFor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/1/4
 * @time 10:21
 * @PROJECT_NAME recruit
 */
@Slf4j
@SpringBootTest
class ApplyForControllerTest {
    @Autowired
    private ApplyForController applyForController;

    @Test
    void add() {
        ApplyFor applyFor = new ApplyFor();
        applyFor.setUserId(1);
        applyFor.setPositionId(1);
        applyForController.add(applyFor);
        log.info("ApplyForId：{}",applyFor.getApplyForId());
    }
}