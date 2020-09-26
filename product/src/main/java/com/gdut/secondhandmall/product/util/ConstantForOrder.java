package com.gdut.secondhandmall.product.util;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/17-18:07
 * @description
 **/
public class ConstantForOrder {
    public static final int UNHANDLED = 0;
    public static final int HANDLED = 1;
    /**
     * redis中存放热销商品的key
     */
    public static final String REDIS_KEY = "bestSellers";
    /**
     * ES中存放商品的的索引名
     */
    public static final String ELASTICSEARCH_INDEX = "online_product";
}
