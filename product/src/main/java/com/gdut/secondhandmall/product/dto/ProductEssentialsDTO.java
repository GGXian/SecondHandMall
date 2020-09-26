package com.gdut.secondhandmall.product.dto;

import lombok.*;

import java.util.Date;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTIme 2020/8/9-19:58
 * @description 热销商品信息模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEssentialsDTO {
    private long productId;
    private String productName;
    private String description;
    private short secondaryDirectory;
    private Date createTime;
    private double price;
    private short visitor;
    private String picUrl;
}
