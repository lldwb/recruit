package com.example.recruit.doc;

import com.example.recruit.config.RedisConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/26
 * @time 15:11
 * @PROJECT_NAME recruit
 */
@Document(indexName = RedisConfig.ES_INDEX + "position", createIndex = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDoc {
    /**
     * 单位编号
     */
    @Id
    @Field(index = false, type = FieldType.Integer)
    private Integer positionId;

    /**
     * 单位名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String positionName;

    /**
     * 所需人数 未招满状态为1(招聘中)，满2000状态为2已招满)
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String positionPositionState;

    /**
     * 所属单位
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String positionAffiliatedUnit;
}
