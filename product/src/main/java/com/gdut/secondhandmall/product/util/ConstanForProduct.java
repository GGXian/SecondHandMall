package com.gdut.secondhandmall.product.util;

import org.springframework.stereotype.Component;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/10-15:10
 * @description 常量类
 **/
@Component
public class ConstanForProduct {
    /**
     * redis中存放热销商品的key
     */
    public static final String REDIS_KEY = "bestSellers";
    /**
     * ES中存放商品的的索引名
     */
    public static final String ELASTICSEARCH_INDEX = "online_product";
    /**
     * 商品提交未审核
     */
    public static final int WAITING_FOR_VERIFING = 0;
    /**
     * 商品提交已通过审核
     */
    public static final int PASS_FROM_VERIFING = 1;
    /**
     * 商品提交未通过审核
     */
    public static final int NOT_PASS_FROM_VERIFING = -1;
}
