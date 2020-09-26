package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.OmsOrderCompletedDao;
import com.gdut.secondhandmall.product.dao.OmsOrderOngoingDao;
import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.model.OmsOrderCompletedDO;
import com.gdut.secondhandmall.product.model.OmsOrderOngoingDO;
import com.gdut.secondhandmall.product.service.OmsOrderCompletedService;
import com.gdut.secondhandmall.product.service.RedisService;
import com.gdut.secondhandmall.product.util.ConstanForProduct;
import com.gdut.secondhandmall.product.util.ESUtil;
import com.gdut.secondhandmall.product.util.TransferUtilForOrder;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/17-10:55
 * @description 处理已完成订单的相关逻辑
 **/
@Service
public class OmsOrderCompletedServiceImpl implements OmsOrderCompletedService {
    @Autowired
    OmsOrderCompletedDao completedDao;
    @Autowired
    OmsOrderOngoingDao ongoingDao;
    @Autowired
    TransferUtilForOrder transferUtilForOrder;
    @Autowired
    ESUtil esUtil;
    @Autowired
    RedisService redisService;

    /**
     * 获取所有订单
     * @param pageParam
     * @return
     */
    @Override
    public List<OmsOrderCompletedDO> getAllOrder(PageParamDTO pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<OmsOrderCompletedDO> omsOrderCompletedDOS = completedDao.selectAll();
        return omsOrderCompletedDOS;
    }

    /**
     * 获取某买家的订单
     * @param pageParam
     * @param openId
     * @return
     */
    @Override
    public List<OmsOrderCompletedDO> getOrderForBuyer(PageParamDTO pageParam, String openId) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<OmsOrderCompletedDO> orderForBuyer = completedDao.getOrderForBuyer(openId);
        return orderForBuyer;
    }

    /**
     * 获取某卖家的订单
     * @param pageParam
     * @param openId
     * @return
     */
    @Override
    public List<OmsOrderCompletedDO> getOrderForSeller(PageParamDTO pageParam, String openId) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<OmsOrderCompletedDO> orderForSeller = completedDao.getOrderForSeller(openId);
        return orderForSeller;
    }

    /**
     * 确认交易完成
     * @param orderId
     * @return
     */
    @Transactional
    @Override
    public boolean confirmCompletion(long orderId) {
        //将mysql中的数据转移
        OmsOrderOngoingDO orderOngoing = ongoingDao.selectByOrderId(orderId);
        if (orderOngoing == null){
            return false;
        }
        if (ongoingDao.deleteByOrderId(orderId) != 1){
            return false;
        }
        OmsOrderCompletedDO orderCompleted = transferUtilForOrder.orderOngoingToOrderCompleted(orderOngoing);
        completedDao.insert(orderCompleted);
        //删除ES中的数据
        try {
            esUtil.deleteDoc(ConstanForProduct.ELASTICSEARCH_INDEX, String.valueOf(orderOngoing.getProductId()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //若是人气商品则删除redis中的数据
        redisService.deleteIfExist(orderOngoing.getProductId());
        return true;
    }
}
