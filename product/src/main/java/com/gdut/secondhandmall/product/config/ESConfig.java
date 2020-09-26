package com.gdut.secondhandmall.product.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTIme 2020/8/8-14:55
 * @description ElasticSearch的配置类
 **/
@Configuration
public class ESConfig {
    /**
     * ES服务器的地址，从配置文件中获取
     */
    @Value("elasticsearch.address")
    private static String address;

    /**
     * ES服务器的端口，默认为9200
     */
    private static final int PORT = 9200;

    /**
     * 连接ES服务器使用的协议，默认为http协议
     */
    private static final String SCHEMENAME = "http";

    /**
     * 创建连接ES服务器的使用的客户端
     * @return 连接ES服务器的使用的客户端
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
        return client;
    }
}
