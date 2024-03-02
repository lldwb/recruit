package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.recruit.doc.UserDoc;
import com.example.recruit.domain.User;
import com.example.recruit.service.UserService;
import com.example.recruit.service.es.EsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.Map;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 16:36
 * @PROJECT_NAME recruit
 */
@Slf4j
@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserService service;
    @Autowired
    private EsService esService;
    @Autowired
    private ElasticsearchOperations template;

    @Test
    void getId() {
//        log.info(String.valueOf(service.getOne(new QueryWrapper<User>().eq("user_id", 1))));
        log.info(String.valueOf(service.getById(1)));
    }

    @Test
    void getList() {
        User user = new User();
//        esService.listNamesByNames(UserDoc.class, 0, 10, BeanUtil.beanToMap(user)).forEach(userDoc -> log.info("用户：{}", userDoc));
        user.setUserName("志");
//        user.setUserId(10);
//        user.setUserGender("女");
        Map<String, Object> map = BeanUtil.beanToMap(user);
        for (String field : map.keySet()) {
            if (map.get(field) != null && !"".equals(map.get(field))) {
                log.info("字段：{}，值：{}", field, map.get(field));
            }
        }
        esService.listNamesByNames(UserDoc.class, 0, 10, BeanUtil.beanToMap(user)).forEach(userDoc -> log.info("用户：{}", userDoc));


//        //构建本地查询器
//        NativeQueryBuilder builder = new NativeQueryBuilder();
//        //使用terms查询，指定查询参数和字段
//        builder.withQuery(query ->
//                        //trem
//                        query.term(t -> t.field("userId").value(10))
//                //match
//                //query.match(m -> m.field(field).query(searchParam))
//        );
//        //如果需要分页，则设置withPageable
//        builder.withPageable(PageRequest.of(0, 10));
//        //执行检索并返回命中的记录
//        SearchHits<UserDoc> hits = template.search(builder.build(), UserDoc.class);
//        hits.forEach(hit -> log.info("数据{}",hit.getContent()));


//        // 创建一个NativeQueryBuilder对象
//        NativeQueryBuilder queryBuilder = new NativeQueryBuilder();
//        // 设置分页信息
//        queryBuilder.withPageable(PageRequest.of(0, 10));
//        // 添加 query
//        queryBuilder.withQuery(q -> {
//            //构建布隆查询
//            return q.bool(bq -> {
//                List<Query> queries = new ArrayList<>();
//                //创建should查询集合，应用在多个字段上
//                for (String field : map.keySet()) {
//                    if (map.get(field) != null && !"".equals(map.get(field))) {
//                        //否则构建普通的term查询
//                        Query query = Query.of(oq -> oq.term(t -> t.field(field).value("" + map.get(field))));
//                        //构建多个termQuery查询，保存到list集合中
//                        queries.add(query);
//                    }
//                }
//                bq.should(queries);
//                return bq;
//            });
//        });
//        SearchHits<UserDoc> hits = template.search(queryBuilder.build(), UserDoc.class);
//        hits.forEach(hit -> log.info("数据{}",hit.getContent()));


    }
}