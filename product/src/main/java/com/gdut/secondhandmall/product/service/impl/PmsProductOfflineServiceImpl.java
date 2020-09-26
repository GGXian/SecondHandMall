package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.PmsProductOfflineDao;
import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.model.PmsProductOfflineDO;
import com.gdut.secondhandmall.product.service.PmsProductOfflineService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-21:22
 * @description 处理已出售商品的相关逻辑
 **/
@Service
public class PmsProductOfflineServiceImpl implements PmsProductOfflineService {
    @Autowired
    PmsProductOfflineDao offlineDao;

    /**
     * 获取所有已出售商品
     * @param pageParam
     * @return
     */
    @Override
    public List<PmsProductOfflineDO> getProductsSold(PageParamDTO pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<PmsProductOfflineDO> pmsProductOfflineDOS = offlineDao.selectAll();
        return pmsProductOfflineDOS;
    }

    /**
     * 获取某用户的已出售商品
     * @param pageParam
     * @param openId
     * @return
     */
    @Override
    public List<PmsProductOfflineDO> getProductsSoldForUser(PageParamDTO pageParam, String openId) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<PmsProductOfflineDO> productsSoldForUser = offlineDao.getProductsSoldForUser(openId);
        return productsSoldForUser;
    }
}
