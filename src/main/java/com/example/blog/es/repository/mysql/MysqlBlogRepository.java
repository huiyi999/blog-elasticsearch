package com.example.blog.es.repository.mysql;

import com.example.blog.es.entity.mysql.MysqlBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : chy
 * @date: 2022-06-20 1:01 p.m.
 */
@Repository
public interface MysqlBlogRepository extends JpaRepository<MysqlBlog, Integer> {
    /**
     * query blog according to createTime desc
     */
    @Query("select e from MysqlBlog e order by e.createTime desc ")
    List<MysqlBlog> queryAll();

    /**
     * fuzzy search
     */
    @Query("select e from MysqlBlog e where e.title like concat('%',:keyword,'%') or e.content like concat('%',:keyword,'%')")
    List<MysqlBlog> queryBlog(@Param("keyword") String keyword);
}
