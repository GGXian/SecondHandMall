package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.PmsProductOnlineDao;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.service.RedisService;
import com.gdut.secondhandmall.product.util.ConstanForProduct;
import com.gdut.secondhandmall.product.util.RedisUtil;
import com.gdut.secondhandmall.product.util.TransferUtilForProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/12-14:10
 * @description 处理Redis数据的相关逻辑
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    PmsProductOnlineDao onlineDao;
    @Autowired
    TransferUtilForProduct transferUtilForProduct;

    /**
     * 获取人气商品
     * @return
     */
    @Override
    public List<ProductEssentialsDTO> getBestSeller() {
        List<ProductEssentialsDTO> products = new ArrayList<>();
        Map<Object, Object> map = redisUtil.hmget(ConstanForProduct.REDIS_KEY);
        for (Object productId : map.keySet()) {
            Object product = map.get(productId);
            products.add((ProductEssentialsDTO) product);
        }
        List<ProductEssentialsDTO> productEssentials = redisUtil.sortDesc(products);
        return productEssentials;
    }

    /**
     * 人气商品更新后，更新redis中的具体商品数据
     * @param product
     * @return
     */
    @Override
    public boolean updateIfExist(ProductEssentialsDTO product) {
        Map<Object, Object> map = redisUtil.hmget(ConstanForProduct.REDIS_KEY);
        Map<String, Object> newMap = new HashMap<>();
        if (!map.containsKey(product.getProductId())){
            return true;
        }
        Object replace = map.replace(product.getProductId(), product);
        if (replace == null){
            return false;
        }
        for (Object o : map.keySet()) {
            newMap.put((String)o, map.get(o));
        }
        redisUtil.del(ConstanForProduct.REDIS_KEY);
        redisUtil.hmset(ConstanForProduct.REDIS_KEY, newMap);
        return true;
    }

    /**
     * 人气商品移除（下架、出售）后，更新redis中的全部数据
     * @param productId
     * @return
     */
    @Override
    public boolean deleteIfExist(long productId) {
        Map<Object, Object> map = redisUtil.hmget(ConstanForProduct.REDIS_KEY);
        Map<String, Object> newMap = new HashMap<>();
        if (!map.containsKey(productId)){
            return false;
        }
        ArrayList<ProductEssentialsDTO> essentials =
                (ArrayList<ProductEssentialsDTO>) onlineDao.selectessentialsSortedByVisitors();
        for (ProductEssentialsDTO essential : essentials) {
            newMap.put(String.valueOf(essential.getProductId()), essential);
        }
        redisUtil.del(ConstanForProduct.REDIS_KEY);
        redisUtil.hmset(ConstanForProduct.REDIS_KEY, newMap);
        return true;
    }

    /**
     * 判断该用户当天是否访问过该商品
     * @param openId
     * @param productId
     * @return
     */
    @Override
    public boolean isViewed(String openId, Long productId) {
        //判断redis中是否有该商品的浏览人数缓存（set类型），若无缓存就增加缓存
        if (!redisUtil.hasKey(productId.toString())){
            redisUtil.sSet(productId.toString(), openId);
            redisUtil.expire(productId.toString(), 60*60*24);
            return true;
        }
        //查看该商品的浏览人数缓存中是否有该用户
        return redisUtil.sHasKey(productId.toString(), openId);
    }

    /**
     * 修改redis中的人气商品
     * @param
     * @return
     */
    @Override
    public boolean updateVisitorIfValid(long productId, String openId) {
        //判断该浏览是否有效
        if (isViewed(openId, productId)){
            return true;
        }
        //若有效则将该用户添加进已浏览用户列表中
        if (redisUtil.sSet(String.valueOf(productId), openId) != 1){
            return false;
        }
        //将该商品的有效浏览数+1
        if (onlineDao.updateVisitors(productId) != 1){
            return false;
        }
        return true;
    }
}
