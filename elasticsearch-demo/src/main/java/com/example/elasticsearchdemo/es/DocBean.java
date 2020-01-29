package com.example.elasticsearchdemo.es;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@Document(indexName = "emsc", type = "_doc", shards = 2, replicas = 0)
public class DocBean {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String fristCode;

    @Field(type = FieldType.Keyword)
    private String secordCode;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Integer)
    private Integer type;

    public DocBean(Long id,String firstCode,String secordCode,String content,Integer type){
        this.id=id;
        this.fristCode=firstCode;
        this.secordCode=secordCode;
        this.content=content;
        this.type=type;
    }
}
