package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.config.MinIOConfig;
import com.example.recruit.config.RabbitConfig;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.Unit;
import com.example.recruit.domain.User;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.UserService;
import com.example.recruit.service.es.EsService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.core.Validate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 8:51
 * @PROJECT_NAME recruit
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService service;
    private final RabbitTemplate template;
    private final EsService esService;
    private final MinioClient minioClient;

    @GetMapping("/getId")
    public BaseResponse<User> getId(Integer id) {
        return success(service.getById(id));
    }

    @GetMapping("/getPhone")
    public BaseResponse<User> getPhone(User user) {
        return success(service.getOne(new QueryWrapper<User>().eq("user_phone", user.getUserPhone())));
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody User user) {
        service.update(user, new UpdateWrapper<User>().eq("user_id", user.getUserId()));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
        return success();
    }

    @DeleteMapping("/delete")
    public BaseResponse delete(Integer userId) {
        User user = new User();
        user.setUserState(0);
        service.update(user, new UpdateWrapper<User>().eq("user_id", userId));
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
        return success();
    }

    @GetMapping("/getList")
    public BaseResponse<List<User>> getList(User user, Integer pageNum, Integer pageSize) {
        List<User> list = new ArrayList<>();
        List<UserDoc> docList;
        if (pageNum == null || pageSize == null) {
            docList = esService.listNamesByNames(UserDoc.class, BeanUtil.beanToMap(user, false, true));
        } else {
            docList = esService.listNamesByNames(UserDoc.class, pageNum, pageSize, BeanUtil.beanToMap(user, false, true));
        }
        docList.forEach(userDoc -> list.add(getUser(userDoc)));
        return success(list);
    }

    @PutMapping("/addHeadPortrait")
    public BaseResponse HeadPortrait(MultipartFile multipartFile, Integer userId) throws IOException {
        try {
            // 获取文件输入流
            InputStream inputStream = multipartFile.getInputStream();

            String sha256Hex = DigestUtil.sha256Hex(multipartFile.getBytes()) + ".jpg";

            // 检测是否已经存在，如果存在则不上传
            log.info("检测是否已经存在，如果存在则不上传");
            try {
                log.info("检测{}是否已经存在，如果存在则不上传", sha256Hex);
                minioClient.getObject(GetObjectArgs.builder().bucket(MinIOConfig.BUCKET).object(sha256Hex).length(0L).build());
                // 判断文件是否存在
//            boolean exists = minioClient.statObject(StatObjectArgs.builder().bucket(MinIOConfig.BUCKET).object(sha256Hex).build()) != null;
            } catch (Exception e) {
                try {
                    log.info("上传{}文件到Minio", sha256Hex);
                    minioClient.putObject(PutObjectArgs.builder().bucket(MinIOConfig.BUCKET).object(sha256Hex).stream(inputStream, multipartFile.getSize(), -1).build());
                } catch (Exception ex) {
                    log.info("上传出错");
                }
            }
            log.info("结束");

            User user = new User();
            user.setUserHeadPortrait(("http://minio.lldwb.top/" + MinIOConfig.BUCKET + "/" + sha256Hex));
            service.update(user, new UpdateWrapper<User>().eq("user_id", userId));
            template.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitUpdate.ROUTING_KEY, UpdateMessage.getUpdateMessage(getUserDoc(user)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return success();
    }

    public static UserDoc getUserDoc(User user) {
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
        userDoc.setUserHeadPortrait(user.getUserHeadPortrait());
        userDoc.setUserIdentityCard(user.getUserIdentityCard());
        return userDoc;
    }

    public static User getUser(UserDoc userDoc) {
        User user = new User();
        user.setUserId(userDoc.getUserId());
        user.setUserGender(userDoc.getUserGender());
        user.setUserAge(userDoc.getUserAge());
        user.setUserName(userDoc.getUserName());
        user.setUserObey(userDoc.getUserObey());
        user.setUserNation(userDoc.getUserNation());
        user.setUserPhone(userDoc.getUserPhone());
        user.setUserStature(userDoc.getUserStature());
        user.setUserState(userDoc.getUserState());
        user.setUserWeight(userDoc.getUserWeight());
        user.setUserPutUp(userDoc.getUserPutUp());
        user.setUserHeadPortrait(userDoc.getUserHeadPortrait());
        user.setUserIdentityCard(userDoc.getUserIdentityCard());
        return user;
    }
}
