package com.gdut.secondhandmall.product.util;

import com.alibaba.fastjson.JSON;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.dto.ProductPictureInUrlDTO;
import com.gdut.secondhandmall.product.model.PmsProductOfflineDO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import com.gdut.secondhandmall.product.model.PmsProductVertifyRecordDO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/17-22:58
 * @description 模型之间的转化工具
 **/
@Component
public class TransferUtilForProduct {
    /**
     * 将完整的在售商品信息转化为简要商品信息
     * @param productOnline
     * @return
     */
    public ProductEssentialsDTO transferOnlineToEssential(PmsProductOnlineDO productOnline){
        ProductPictureInUrlDTO url = null;
        ProductEssentialsDTO essential = new ProductEssentialsDTO();
        essential.setProductId(productOnline.getProductId());
        essential.setProductName(productOnline.getProductName());
        essential.setSecondaryDirectory(productOnline.getDirectoryId());
        essential.setDescription(productOnline.getDescription());
        url = JSON.parseObject(productOnline.getPicUrl(), ProductPictureInUrlDTO.class);
        essential.setPicUrl(url.getMain());
        essential.setCreateTime(productOnline.getTime());
        essential.setVisitor(productOnline.getVisitor());
        essential.setPrice(productOnline.getPrice().doubleValue());
        return essential;
    }

    /**
     * 将在售商品信息转化为已出售商品信息
     * @param productOnline
     * @return
     */
    public PmsProductOfflineDO onlineToOffline(PmsProductOnlineDO productOnline){
        PmsProductOfflineDO product = new PmsProductOfflineDO();
        product.setOpenid(productOnline.getOpenid());
        product.setProductId(productOnline.getProductId());
        product.setProductName(productOnline.getProductName());
        product.setDirectoryId(productOnline.getDirectoryId());
        product.setDescription(productOnline.getDescription());
        product.setPrice(productOnline.getPrice());
        product.setPicUrl(productOnline.getPicUrl());
        product.setPublishTime(productOnline.getTime());
        product.setDealTime(new Date());
        product.setVisitor(productOnline.getVisitor());
        product.setOriginalPrice(productOnline.getOriginalPrice());
        return product;
    }

    /**
     * 将已出售商品信息转化为在售商品信息
     * @param offline
     * @return
     */
    public PmsProductOnlineDO offlineToOnline(PmsProductOfflineDO offline){
        PmsProductOnlineDO online = new PmsProductOnlineDO();
        online.setOpenid(offline.getOpenid());
        online.setProductId(offline.getProductId());
        online.setProductName(offline.getProductName());
        online.setDirectoryId(offline.getDirectoryId());
        online.setDescription(offline.getDescription());
        online.setPrice(offline.getPrice());
        online.setPicUrl(offline.getPicUrl());
        online.setTime(offline.getPublishTime());
        online.setVisitor(offline.getVisitor());
        online.setOriginalPrice(offline.getOriginalPrice());
        return online;
    }

    /**
     * 将审核记录模型转化为在售商品模型
     * @param vertifyRecord
     * @return
     */
    public PmsProductOnlineDO verificationToOnline(PmsProductVertifyRecordDO vertifyRecord){
        PmsProductOnlineDO onlineDO = new PmsProductOnlineDO();
        onlineDO.setOpenid(vertifyRecord.getOpenid());
        onlineDO.setProductId(vertifyRecord.getProductId());
        onlineDO.setProductName(vertifyRecord.getProductName());
        onlineDO.setDirectoryId(vertifyRecord.getDirectoryId());
        onlineDO.setPicUrl(vertifyRecord.getPicUrl());
        onlineDO.setDescription(vertifyRecord.getDescription());
        onlineDO.setOriginalPrice(vertifyRecord.getOriginalPrice());
        onlineDO.setPrice(vertifyRecord.getPrice());
        onlineDO.setTime(vertifyRecord.getCreateTime());
        onlineDO.setVisitor((short) 0);
        return onlineDO;
    }

}
