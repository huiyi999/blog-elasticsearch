package com.example.blog.es.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : chy
 * @date: 2022-06-20 12:46 p.m.
 * <p>
 * CREATE TABLE `t_blog` (
 * `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'auto_increment id',
 * `title` varchar(60) DEFAULT NULL COMMENT 'blog title',
 * `author` varchar(60) DEFAULT NULL COMMENT 'blog author',
 * `content` mediumtext COMMENT 'blog content',
 * `create_time` datetime DEFAULT NULL COMMENT 'create time',
 * `update_time` datetime DEFAULT NULL COMMENT 'update time',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4
 */

@Data
@Table(name = "t_blog")
@Entity
public class MysqlBlog {

    // PRIMARY KEY: AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    @Column(columnDefinition = "mediumtext")
    private String content;
    private Date createTime;
    private Date updateTime;
}
