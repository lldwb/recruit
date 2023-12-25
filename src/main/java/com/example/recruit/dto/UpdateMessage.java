package com.example.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/1
 * @time 11:51
 * @PROJECT_NAME file_saving_tool_backend
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessage<T> extends Message<T> {
    public static <T> UpdateMessage getUpdateMessage(T data) {
        UpdateMessage<T> updateMessage = new UpdateMessage<>();
        updateMessage.setClazz(data.getClass().getName());
        updateMessage.setData(data);
        return updateMessage;
    }

    private String clazz;
    private T data;
}
