package com.gdut.secondhandmall.product.service;

import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/12-14:02
 * @description 更新商品的浏览人数：规定一种商品的浏览人数一人一天只能算一次
 **/
public interface RedisService {
    /**
     * 判断此次浏览是否为有效浏览
     * @param openId
     * @return true为无效数据，false为有效数据
     */
    boolean isViewed(String openId, Long productId);

    /**
     * 更新浏览人数
     * @param
     * @return
     */
    boolean updateVisitorIfValid(long productId, String openId);

    /**
     * 获取热销商品
     * @return
     */
    List<ProductEssentialsDTO> getBestSeller();

    boolean updateIfExist(ProductEssentialsDTO product);

    boolean deleteIfExist(long productId);

}
