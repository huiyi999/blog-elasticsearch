package com.example.blog.es;

import com.example.blog.es.entity.es.EsBlog;
import com.example.blog.es.entity.mysql.MysqlBlog;
import com.example.blog.es.repository.es.EsBlogRepository;
import com.example.blog.es.repository.mysql.MysqlBlogRepository;
import com.example.blog.es.service.es.EsBlogService;
import com.example.blog.es.service.mysql.MysqlBlogService;
import com.example.blog.es.config.ESClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class BlogEsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    EsBlogRepository esBlogRepository;

    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    @Test
    public void testEs() {
        Iterable<EsBlog> all = esBlogRepository.findAll();
        Iterator<EsBlog> esBlogIterator = all.iterator();
        for (EsBlog esBlog : all) {
            System.out.println(esBlog.toString());
        }
        EsBlog esBlog = esBlogIterator.next();

        log.info("【es+springboot】esBlog={}", esBlog);

        Optional<EsBlog> blog = esBlogRepository.findById(1);
        System.out.println(blog.isPresent());
        log.info("【es+springboot】esBlog={}", blog.get());
    }

    @Test
    public void testMySQL() {

        List<MysqlBlog> all = mysqlBlogRepository.findAll();
        log.info("【Search all blog】all={}", all.size());

        for (MysqlBlog blog : all) {
            log.info("string={}", blog.toString());
        }
    }

    @Autowired
    MysqlBlogService mysqlBlogService;

    @Autowired
    EsBlogService esBlogService;

    @Test
    public void testESQuery1() {
        Pageable pageable = PageRequest.of(10, 20);
        String param = "springboot";

        // Page<EsBlog> blogs = esBlogService.findAll(pageable);
        Iterable<EsBlog> all = esBlogService.findAll();

        List<EsBlog> blogs1 = esBlogService.findByTitleOrContent(param);
        System.out.println(blogs1.size());

        Page<EsBlog> blogs2 = esBlogService.findByTitleOrContent(param,pageable);
        System.out.println(blogs2.getContent());

        // blogs2.forEach(blog -> System.out.println(blog));

        // Page<EsBlog> blogs = esBlogService.getByKey(param, pageable);
        // System.out.println(blogs.get());
        // System.out.println(blogs.stream().count());
    }

    /**
     * java configuration
     *
     * @throws IOException
     */
    @Test
    public void testESQuery2() throws IOException {
        // RestHighLevelClient client = ESClientConfig.e
        String index = "blog";

        RestHighLevelClient client = ESClient.getClient();
        SearchRequest request = new SearchRequest(index);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.idsQuery().addIds("1", "3"));
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
