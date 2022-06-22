package com.example.blog.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author : chy
 * @date: 2022-05-18 7:56 p.m.
 */
@Configuration
// @EnableElasticsearchRepositories(basePackages = "com.example.blog.es.repository.es")
public class ESClientConfig extends AbstractElasticsearchConfiguration {



    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        // create HttpHost object
        // 9300 port is used as transport port and 9200 port is known as http port.
        HttpHost httpHost = new HttpHost("localhost", 9200);
        // HttpHost httpHost = new HttpHost("127.0.0.1", 9200);

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withBasicAuth("elastic", "changeme")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
