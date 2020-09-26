package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.PmsProductOffshelvesDao;
import com.gdut.secondhandmall.product.dao.PmsProductOnlineDao;
import com.gdut.secondhandmall.product.model.PmsProductOffshelvesDO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import com.gdut.secondhandmall.product.service.PmsProductOffshelvesService;
import com.gdut.secondhandmall.product.util.ConstanForProduct;
import com.gdut.secondhandmall.product.util.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-17:34
 * @description 处理下架商品的相关逻辑
 **/
@Service
public class PmsProductOffshelvesServiceImpl implements PmsProductOffshelvesService {
    @Autowired
    PmsProductOffshelvesDao offshelvesDao;
    @Autowired
    PmsProductOnlineDao onlineDao;
    @Autowired
    ESUtil esUtil;

    /**
     * 将商品下架
     * @param offshelvesDO
     * @return
     */
    @Override
    public boolean putOffShelves(PmsProductOffshelvesDO offshelvesDO) {
        //将商品从在售商品表中删除
        PmsProductOnlineDO onlineDO  = getAndDelete(offshelvesDO);
        System.out.println("online:" + onlineDO);
        System.out.println("offshelves:" + offshelvesDO);
        if(onlineDO == null){
            return false;
        }
        //将商品从ES中删除
        try {
            esUtil.deleteDoc(ConstanForProduct.ELASTICSEARCH_INDEX, onlineDO.getProductId().toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //添加用户id和时间
        offshelvesDO.setOpenid(onlineDO.getOpenid());
        offshelvesDO.setTime(new Date());
        //将商品放进下架商品表中
        offshelvesDao.insert(offshelvesDO);
        return true;
    }

    /**
     * 从Mysql中获取并删除商品信息
     * @param offshelvesDO
     * @return
     */
    @Transactional
    PmsProductOnlineDO getAndDelete(PmsProductOffshelvesDO offshelvesDO){
        PmsProductOnlineDO onlineDO = onlineDao.selectByProductId(offshelvesDO.getProductId());
        if(onlineDO == null){
            return null;
        }
        if(onlineDao.deleteByPrimaryKey(onlineDO.getId()) != 1){
            return null;
        }
        return onlineDO;
    }
}
