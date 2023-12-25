package com.example.recruit.service.consumer;

import cn.hutool.core.convert.Convert;
import com.example.recruit.config.RabbitUpdate;
import com.example.recruit.dto.UpdateMessage;
import com.example.recruit.service.es.EsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/1
 * @time 11:11
 * @PROJECT_NAME file_saving_tool_backend
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerUpdate {
    private final EsService esService;
    private final ElasticsearchOperations template;
//    private final EsSyncService esSyncService;

    @RabbitListener(queues = RabbitUpdate.QUEUE_NAME)
    public void esUpdate(UpdateMessage updateMessage) throws IntrospectionException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        log.info("开始消费"+updateMessage.toString());
        template.save(Convert.convert(Class.forName(updateMessage.getClazz()),updateMessage.getData()));
        log.info("消费成功"+updateMessage.toString());
    }
}
