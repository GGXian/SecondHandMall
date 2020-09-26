package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.OmsOrderOngoingDao;
import com.gdut.secondhandmall.product.dao.PmsProductOfflineDao;
import com.gdut.secondhandmall.product.dao.PmsProductOnlineDao;
import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.model.OmsOrderOngoingDO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import com.gdut.secondhandmall.product.service.OmsOrderOngoingService;
import com.gdut.secondhandmall.product.service.RedisService;
import com.gdut.secondhandmall.product.util.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/16-20:30
 * @description 处理未完成订单的相关逻辑
 **/
@Service
public class OmsOrderOngoingServiceImpl implements OmsOrderOngoingService {
    @Autowired
    OmsOrderOngoingDao orderOngoingDao;
    @Autowired
    PmsProductOnlineDao productOnlineDao;
    @Autowired
    PmsProductOfflineDao productOfflineDao;
    @Autowired
    IDGeneratorForOrder idGeneratorForOrder;
    @Autowired
    ESUtil esUtil;
    @Autowired
    RedisService redisService;
    @Autowired
    TransferUtilForProduct transferUtilForProduct;

    /**
     * 获取所有未完成订单
     * @param pageParam
     * @return
     */
    @Override
    public List<OmsOrderOngoingDO> getAllUnfinishedOrders(PageParamDTO pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<OmsOrderOngoingDO> omsOrderOngoings = orderOngoingDao.selectAll();
        return omsOrderOngoings;
    }

    /**
     * 获取某买家的未完成订单
     * @param pageParam
     * @param openId
     * @return
     */
    @Override
    public List<OmsOrderOngoingDO> getAllUnfinishedOrdersForBuyer(PageParamDTO pageParam, String openId) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<OmsOrderOngoingDO> allUnfinishedOrdersForBuyer = orderOngoingDao.getAllUnfinishedOrdersForBuyer(openId);
        return allUnfinishedOrdersForBuyer;
    }

    /**
     * 获取某卖家的未完成订单
     * @param pageParam
     * @param openId
     * @return
     */
    @Override
    public List<OmsOrderOngoingDO> getAllUnfinishedOrdersForseller(PageParamDTO pageParam, String openId) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<OmsOrderOngoingDO> allUnfinishedOrdersForseller = orderOngoingDao.getAllUnfinishedOrdersForseller(openId);
        return allUnfinishedOrdersForseller;
    }

    /**
     * 提交新订单
     * @param sessionKey
     * @param productId
     * @return
     */
    @Override
    public boolean postNewOrder(String sessionKey, long productId) {
        //将mysql在售商品表中的商品信息转移到已售商品表中和移除ES中的商品信息
        PmsProductOnlineDO product = moveOnlineToOffline(productId);
        if (product == null){
            return false;
        }
        try {
            esUtil.deleteDoc(ConstantForOrder.ELASTICSEARCH_INDEX, String.valueOf(productId));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //获取商品信息并新增订单信息
        OmsOrderOngoingDO order = new OmsOrderOngoingDO();
        order.setOrderId(idGeneratorForOrder.nextId());
        order.setProductId(productId);
        order.setBuyerId(sessionKey);
        order.setPayType(0);
        order.setSellerId(product.getOpenid());
        order.setTotalAmount(product.getPrice());
        order.setTime(new Date());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dt = LocalDateTime.now().plusDays(7);
        String time = dtf.format(dt);
        try {
            order.setAutoConfirmTime(TimeUtil.getTimeForMysql(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderOngoingDao.insert(order);
        //若redis有则下架
        redisService.deleteIfExist(productId);
        return true;
    }

    /**
     * 出售商品后将商品下架
     * @param productId
     * @return
     */
    @Transactional
    public PmsProductOnlineDO moveOnlineToOffline(long productId){
        PmsProductOnlineDO product = productOnlineDao.selectByProductId(productId);
        System.out.println(product);
        productOnlineDao.deleteByPrimaryKey(product.getId());
        productOfflineDao.insert(transferUtilForProduct.onlineToOffline(product));
        return product;
    }


}
