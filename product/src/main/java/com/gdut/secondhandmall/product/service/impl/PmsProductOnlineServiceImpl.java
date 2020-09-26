package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.PmsProductOnlineDao;
import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.model.PmsProductOfflineDO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import com.gdut.secondhandmall.product.service.PmsProductOnlineService;
import com.gdut.secondhandmall.product.util.TransferUtilForProduct;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/10-11:50
 * @description 处理在售商品信息的相关逻辑
 **/
@Service
public class PmsProductOnlineServiceImpl implements PmsProductOnlineService {
    @Autowired
    PmsProductOnlineDao onlineDao;
    @Autowired
    RedisServiceImpl updateVisitorService;
    @Autowired
    TransferUtilForProduct transferUtilForProduct;

    /**
     * 获取所有商品的概要信息
     * @return
     */
    @Override
    public List<ProductEssentialsDTO> selectessentials() {
        return onlineDao.selectessentials();
    }

    /**
     * 获取所有在售商品信息
     * @param pageParam
     * @return
     */
    @Override
    public List<PmsProductOnlineDO> getProductsForSale(PageParamDTO pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<PmsProductOnlineDO> pmsProductOnlineDOS = onlineDao.selectAll();
        return pmsProductOnlineDOS;
    }

    /**
     * 获取某商家的所有在售商品信息
     * @param pageParam
     * @param openId
     * @return
     */
    @Override
    public List<PmsProductOnlineDO> getProductsForSaleForUser(PageParamDTO pageParam, String openId) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<PmsProductOnlineDO> productsForSaleForUser = onlineDao.getProductsForSaleForUser(openId);
        return productsForSaleForUser;
    }

    /**
     * 获取在售商品详情
     * @param productId
     * @param openId
     * @return
     */
    @Override
    public PmsProductOnlineDO getProductByProductId(long productId, String openId) {
        System.out.println("openid:" + openId);
        PmsProductOnlineDO pmsProductOnlineDO = onlineDao.selectByProductId(productId);
        updateVisitorService.updateVisitorIfValid(productId, openId);
        return pmsProductOnlineDO;
    }

    /**
     * 同时更新mysql和redis中的商品数据
     * @param product
     * @return
     */
    @Override
    public boolean updateProductByProductId(PmsProductOnlineDO product) {
        if (onlineDao.updateByProductId(product) != 1){
            return false;
        }
        updateVisitorService.updateIfExist(transferUtilForProduct.transferOnlineToEssential(product));
        return true;
    }


}
