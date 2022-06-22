package com.example.blog.es.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author : chy
 * @date: 2022-05-18 7:56 p.m.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.blog.es.repository.es")
public class ESClient {

    @Bean
    public static RestHighLevelClient getClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "changeme"));

        // create HttpHost object
        // 9300 port is used as transport port and 9200 port is known as http port.
        HttpHost httpHost = new HttpHost("localhost", 9200);
        // HttpHost httpHost = new HttpHost("127.0.0.1", 9200);


        RestClientBuilder builder = RestClient.builder(httpHost)
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestHighLevelClient client = new RestHighLevelClient(builder);

        return client;
    }

    // @Bean
    // public ElasticsearchOperations elasticsearchTemplate() {
    //     return new ElasticsearchRestTemplate(getClient());
    // }
}
