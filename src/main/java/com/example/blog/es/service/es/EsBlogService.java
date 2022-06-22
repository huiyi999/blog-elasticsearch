package com.example.blog.es.service.es;

import com.example.blog.es.entity.es.EsBlog;
import com.example.blog.es.repository.es.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author : chy
 * @date: 2022-06-21 2:33 p.m.
 */

@Service
public class EsBlogService {

    @Autowired
    EsBlogRepository esBlogRepository;

    // @Autowired
    // ElasticsearchTemplate elasticsearchTemplate;

    public Iterable<EsBlog> findAll() {
        return esBlogRepository.findAll();
    }

    public Page<EsBlog> findAll(Pageable pageable) {
        return esBlogRepository.findAll(pageable);
    }

    public Page<EsBlog> getByKey(String key, Pageable pageable) {
        if (!StringUtils.hasLength(key)) {
            return esBlogRepository.findAll(pageable);
        }
        return esBlogRepository.findByTitleOrContent(key, key, pageable);
    }

    public List<EsBlog> findByTitle(String key) {
        return esBlogRepository.findByTitle(key);
    }

    public List<EsBlog> findByTitleOrContent(String key) {
        if (!StringUtils.hasLength(key)) {
            return (List<EsBlog>) esBlogRepository.findAll();
        }
        return esBlogRepository.findByTitleOrContent(key, key);
    }

    public Page<EsBlog> findByTitleOrContent(String key, Pageable pageable) {
        return esBlogRepository.findByTitleOrContent(key, key, pageable);
    }

    public List<EsBlog> findByContent(String key) {
        return esBlogRepository.findByContent(key);
    }

    public List<EsBlog> findByContentLike(String key) {
        return esBlogRepository.findByContentLike(key);
    }
}
