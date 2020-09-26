package com.gdut.secondhandmall.product.service;

import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.model.PmsProductOfflineDO;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-21:21
 * @description
 **/
public interface PmsProductOfflineService {
    List<PmsProductOfflineDO> getProductsSold(PageParamDTO pageParam);

    List<PmsProductOfflineDO> getProductsSoldForUser(PageParamDTO pageParam, String openId);
}
