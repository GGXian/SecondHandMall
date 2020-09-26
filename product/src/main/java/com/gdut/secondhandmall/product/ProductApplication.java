package com.gdut.secondhandmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTIme 2020/8/7-15:46
 * @description
 **/
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.gdut.secondhandmall.product.dao")
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
