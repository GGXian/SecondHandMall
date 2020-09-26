package com.gdut.secondhandmall.product.service;

import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/10-11:12
 * @description
 **/
public interface PmsProductOnlineService {
    List<ProductEssentialsDTO> selectessentials();

    List<PmsProductOnlineDO> getProductsForSale(PageParamDTO pageParam);

    List<PmsProductOnlineDO> getProductsForSaleForUser(PageParamDTO pageParam, String openId);

    PmsProductOnlineDO getProductByProductId(long productId, String openId);

    boolean updateProductByProductId(PmsProductOnlineDO product);
}
