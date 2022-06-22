package com.example.blog.es.service.mysql;

import com.example.blog.es.entity.mysql.MysqlBlog;
import com.example.blog.es.repository.mysql.MysqlBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : chy
 * @date: 2022-06-21 2:40 p.m.
 */

@Service
public class MysqlBlogService {

    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    public List<MysqlBlog> queryAll() {
        return mysqlBlogRepository.queryAll();
    }

    public List<MysqlBlog> queryBlog(String keyword) {
        return mysqlBlogRepository.queryBlog(keyword);
    }

    public Optional<MysqlBlog> findById(Integer id) {
        return mysqlBlogRepository.findById(id);
    }
}
