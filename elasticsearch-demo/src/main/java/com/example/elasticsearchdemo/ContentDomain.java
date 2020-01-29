package com.example.elasticsearchdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 星闻社
 */
@Document(indexName = "content",type = "docs", shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDomain implements Serializable {

    private static final long serialVersionUID = 727207407963474156L;
    /**
     * uuid
     */
    @Id
    protected String uuid;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Long)
    protected Date createTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Long)
    protected Date updateTime;

    /**
     * 创建者
     */
    @Field(type = FieldType.Text)
    protected String creator;
    /**
     * 更新者
     */
    @Field(type = FieldType.Text)
    protected String modifier;

    /**
     * 是否已删除
     */
    @Field(type = FieldType.Boolean)
    protected Boolean delFlag = false;


    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Long)
    private Integer contentType;
    @Field(type = FieldType.Text)
    private String movieUrl;
    @Field(type = FieldType.Text)
    private String picUrl;
    @Field(type = FieldType.Text)
    private String starIds;
    @Field(type = FieldType.Long)
    private Integer limitNum;
    @Field(type = FieldType.Long)
    private Integer minPraiseNum;
    @Field(type = FieldType.Long)
    private Integer falseLikeNum;
    @Field(type = FieldType.Long)
    private Integer likeNum;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;
    @Field(type = FieldType.Long)
    private Integer falseHis;
    @Field(type = FieldType.Long)
    private Integer reviewStatus;
    @Field(type = FieldType.Text)
    private String reviewReason;
    @Field(type = FieldType.Text)
    private String userId;
    @Field(type = FieldType.Long)
    private Date releaseTime;
    @Field(type = FieldType.Long)
    private Integer shareNum;
    @Field(type = FieldType.Long)
    private Integer falseShareNum;
    @Field(type = FieldType.Long)
    private Integer weight;
    @Field(type = FieldType.Text)
    private String nickName;
    @Field(type = FieldType.Text)
    private String photo;

}
