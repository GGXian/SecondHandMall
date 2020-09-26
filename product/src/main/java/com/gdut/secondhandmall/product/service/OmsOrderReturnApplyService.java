package com.gdut.secondhandmall.product.service;


import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.VerificationDTOForOrder;
import com.gdut.secondhandmall.product.model.OmsOrderReturnApplyDO;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/17-11:17
 * @description
 **/
public interface OmsOrderReturnApplyService {
    boolean putNewApply(long orderId, String reason);

    List<OmsOrderReturnApplyDO> getAll(PageParamDTO pageParam);

    List<OmsOrderReturnApplyDO> getOrderForBuyer(PageParamDTO pageParam, String openId);

    boolean completeVerification(VerificationDTOForOrder verification);
}
