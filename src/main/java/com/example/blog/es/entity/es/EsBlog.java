package com.example.blog.es.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author : chy
 * @date: 2022-06-20 12:51 p.m.
 */
@Data
/**
 * useServerConfiguration = true 使用线上的配置,createIndex 在项目启动的时候不要创建索引，通常在 kibana 中已经配置过了
 * @Document(indexName = "blog", type = "_doc", useServerConfiguration = true, createIndex = false)
 * type is removed
 */
@Document(indexName = "blog", createIndex = false)
public class EsBlog {

    @Id
    private Integer id;
    @Field(index = true, type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String author;
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date createTime;
    // @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date updateTime;
}
