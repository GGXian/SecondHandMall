package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.PmsProductOnlineDao;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.service.ScheduleRefreshService;
import com.gdut.secondhandmall.product.util.ConstanForProduct;
import com.gdut.secondhandmall.product.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/10-20:50
 * @description 处理定时任务的相关逻辑
 **/
@Service
public class ScheduleRefreshServiceImpl implements ScheduleRefreshService {
    @Autowired
    PmsProductOnlineDao onlineDao;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 每隔一个小时更新热销商品
     * @return
     */
    @Scheduled(cron = "*/30 * * * * *")
    @Override
    public boolean updateBestSellers() {
        HashMap<String, Object> bestSellersmap = new HashMap<>();
        List<ProductEssentialsDTO> bestSellersList = onlineDao.selectessentialsSortedByVisitors();
        if (redisUtil.hasKey(ConstanForProduct.REDIS_KEY)){
            redisUtil.del(ConstanForProduct.REDIS_KEY);
        }
        for (ProductEssentialsDTO productEssentialsDTO : bestSellersList) {
            bestSellersmap.put(String.valueOf(productEssentialsDTO.getProductId()), productEssentialsDTO);
        }
        return redisUtil.hmset(ConstanForProduct.REDIS_KEY, bestSellersmap);
    }
}
