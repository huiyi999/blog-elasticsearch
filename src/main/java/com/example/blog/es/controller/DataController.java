package com.example.blog.es.controller;

import com.example.blog.es.entity.es.EsBlog;
import com.example.blog.es.entity.mysql.MysqlBlog;
import com.example.blog.es.service.es.EsBlogService;
import com.example.blog.es.service.mysql.MysqlBlogService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : chy
 * @date: 2022-06-20 2:46 p.m.
 */

@RestController
@Slf4j
public class DataController {
    private static final String MYSQL = "MySQL";
    private static final String ES = "ElasticSearch";
    Pageable pageable = PageRequest.of(10, 20);

    @Autowired
    MysqlBlogService mysqlBlogService;

    @Autowired
    EsBlogService esBlogService;

    /**
     * return all blog data in mysql
     */
    @GetMapping("/blogs")
    public Object blogList() {
        List<MysqlBlog> mysqlBlogs = mysqlBlogService.queryAll();
        return mysqlBlogs;
    }

    @GetMapping("/blog/{id}")
    public Object blog(@PathVariable Integer id) {
        Optional<MysqlBlog> blog = mysqlBlogService.findById(id);
        // log.info("find by id ={}", id);
        // Optional<EsBlog> blog = esBlogRepository.findById(id);
        // System.out.println(blog.isPresent());
        return blog.get();
    }

    @PostMapping("/search")
    public Object search(@RequestBody Param param) {
        Map<String, Object> map = new HashMap<>();
        // count time
        StopWatch watch = new StopWatch();
        watch.start();

        String type = param.getType();

        // mysql search
        if (MYSQL.equals(type)) {
            List<MysqlBlog> mysqlBlogs = mysqlBlogService.queryBlog(param.getKeyword());
            map.put("list", mysqlBlogs);
            System.out.println(mysqlBlogs.size());

            // es search
        } else if (ES.equals(type)) {

            // BoolQueryBuilder builder = QueryBuilders.boolQuery();
            // builder.should(QueryBuilders.matchPhraseQuery("title", param.getKeyword()));
            // builder.should(QueryBuilders.matchPhraseQuery("content", param.getKeyword()));
            // String s = builder.toString();
            // // log.info("s={}", s);

            System.out.println("key: "+param.getKeyword());
            List<EsBlog> esBlogs = esBlogService.findByTitleOrContent(param.getKeyword());

            System.out.println(esBlogs.size());

            map.put("list", esBlogs);
        } else {
            return "Sorry, invalid operation!";
        }

        watch.stop();
        // count consuming time
        long millis = watch.getTotalTimeMillis();
        map.put("duration", millis);
        return map;
    }

    @Data
    private static class Param {
        // type: mysql, es
        private String type;
        private String keyword;
    }
}
