package com.example.blog.es.controller;

import com.example.blog.es.entity.mysql.MysqlBlog;
import com.example.blog.es.repository.mysql.MysqlBlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author : chy
 * @date: 2022-06-20 12:56 p.m.
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    @GetMapping("/")
    public String index() {
        List<MysqlBlog> all = mysqlBlogRepository.findAll();
        log.info("【Search all blog】all={}", all.size());

        return "index.html";
    }
}
