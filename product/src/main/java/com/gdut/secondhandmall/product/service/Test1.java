package com.gdut.secondhandmall.product.service;

import com.gdut.secondhandmall.product.service.impl.PmsProductOnlineServiceImpl;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/10-16:35
 * @description
 **/
@Component
public class Test1 {
    private ApplicationContext applicationContext;

    public Test1(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void test(){
        System.out.println(applicationContext.getBean("redisTemplate"));
//        Jedis jedis = new Jedis("192.168.59.129", 6379);
//        System.out.println(jedis.exists("aaa"));

    }
}
