package com.gdut.secondhandmall.product.service;


import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.model.OmsOrderCompletedDO;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/17-10:53
 * @description
 **/
public interface OmsOrderCompletedService {
    List<OmsOrderCompletedDO> getAllOrder(PageParamDTO pageParam);

    List<OmsOrderCompletedDO> getOrderForBuyer(PageParamDTO pageParam, String openId);

    List<OmsOrderCompletedDO> getOrderForSeller(PageParamDTO pageParam, String openId);

    boolean confirmCompletion(long orderId);
}
