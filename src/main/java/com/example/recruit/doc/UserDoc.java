package com.example.recruit.doc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.recruit.config.RedisConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 用户文档对象
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 11:04
 * @PROJECT_NAME recruit
 */@Document(indexName = RedisConfig.ES_INDEX + "user", createIndex = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDoc {
    /**
     * id
     */
    @Id
    @Field(index = false, type = FieldType.Integer)
    private Integer userId;

    /**
     * 手机号码
     */
    @Field(type = FieldType.Long)
    private Long userPhone;

    /**
     * 用户名
     */    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String userName;

    /**
     * 性别
     */    @Field(type = FieldType.Text)
    private String userGender;

    /**
     * 年龄
     */@Field(type = FieldType.Integer)
    private Integer userAge;

    /**
     * 民族
     */@Field(type = FieldType.Text)
    private String userNation;

    /**
     * 身高
     */
    @Field(type = FieldType.Double)
    private Double userStature;

    /**
     * 体重
     */@Field(type = FieldType.Double)
    private Double userWeight;

    /**
     * 是否服从分配 0未是 1为否
     */
    @Field(type = FieldType.Integer)
    private Integer userObey;

    /**
     * 是否住宿 0未是 1为否
     */
    @Field(type = FieldType.Integer)
    private Integer userPutUp;

    /**
     * 状态
     */
    @Field(type = FieldType.Integer)
    private Integer userState;
}
