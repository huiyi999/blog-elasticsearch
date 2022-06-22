package com.example.blog.es.repository.es;

import com.example.blog.es.entity.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : chy
 * @date: 2022-06-20 1:06 p.m.
 */
@Repository
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, Integer> {

    /**
     * search by title or content
     *
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    Page<EsBlog> findByTitleOrContentLike(String title, String content, @PageableDefault(size = 5) Pageable pageable);

    List<EsBlog> findByTitle(String title);

    List<EsBlog> findByContent(String content);

    List<EsBlog> findByContentLike(String content);

    List<EsBlog> findByTitleOrContent(String title, String content);

    Page<EsBlog> findByTitleOrContent(String title, String content, Pageable pageable);

    // findByTitleLikeOrContentLike
}


