package com.gdut.secondhandmall.product.service;


import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.model.OmsOrderOngoingDO;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/16-20:29
 * @description
 **/
public interface OmsOrderOngoingService {
    List<OmsOrderOngoingDO> getAllUnfinishedOrders(PageParamDTO pageParam);

    List<OmsOrderOngoingDO> getAllUnfinishedOrdersForBuyer(PageParamDTO pageParam, String openId);

    List<OmsOrderOngoingDO> getAllUnfinishedOrdersForseller(PageParamDTO pageParam, String openId);

    boolean postNewOrder(String sessionKey, long productId);
}
