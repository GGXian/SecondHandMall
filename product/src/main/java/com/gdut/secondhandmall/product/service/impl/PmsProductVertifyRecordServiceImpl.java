package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.PmsProductOnlineDao;
import com.gdut.secondhandmall.product.dao.PmsProductVertifyRecordDao;
import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.dto.VerificationDTOForProduct;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import com.gdut.secondhandmall.product.model.PmsProductVertifyRecordDO;
import com.gdut.secondhandmall.product.service.PmsProductVertifyRecordService;
import com.gdut.secondhandmall.product.util.ConstanForProduct;
import com.gdut.secondhandmall.product.util.ESUtil;
import com.gdut.secondhandmall.product.util.TransferUtilForProduct;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-16:27
 * @description 处理商品审核记录的相关逻辑
 **/
@Service
public class PmsProductVertifyRecordServiceImpl implements PmsProductVertifyRecordService {
    @Autowired
    PmsProductVertifyRecordDao vertifyRecordDao;
    @Autowired
    PmsProductOnlineDao onlineDao;
    @Autowired
    TransferUtilForProduct transferUtilForProduct;
    @Autowired
    ESUtil esUtil;

    /**
     * 增加新商品审核记录
     * @param record
     * @return
     */
    @Override
    public int insertNewRecord(PmsProductVertifyRecordDO record) {
        return vertifyRecordDao.insert(record);
    }

    /**
     * 获取所有商品审核记录
     * @param pageParam
     * @return
     */
    @Override
    public List<PmsProductVertifyRecordDO> getVertifyRecords(PageParamDTO pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<PmsProductVertifyRecordDO> vertifyRecordDOS = vertifyRecordDao.selectAll();
        System.out.println(vertifyRecordDOS.size());
        return vertifyRecordDOS;
    }

    /**
     * 获取某用户的商品审核记录
     * @param pageParam
     * @param openId
     * @return
     */
    @Override
    public List<PmsProductVertifyRecordDO> getVertifyRecordsForUser(PageParamDTO pageParam, String openId) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
        List<PmsProductVertifyRecordDO> vertifyRecordDOS = vertifyRecordDao.selectByOpenID(openId);
        return vertifyRecordDOS;
    }

    /**
     * 获取详细的商品审核记录.
     * @param productId
     * @return
     */
    @Override
    public PmsProductVertifyRecordDO getVerifyRecordDetail(long productId) {
        PmsProductVertifyRecordDO verifyRecordDetail = vertifyRecordDao.getVerifyRecordDetail(productId);
        return verifyRecordDetail;
    }

    /**
     * 管理员完成商品审核
     * @param verification
     * @return
     */
    @Override
    public boolean completeVerification(VerificationDTOForProduct verification) {
        //如果isPassed为空就返回错误返回值-1
        if (verification.getIsPassed() == null){
            return false;
        }
        long productId = verification.getProductId();
        boolean isPassed = verification.getIsPassed().equals("yes");
        //如果不通过就留在审核表中
        if (!isPassed){
            return (vertifyRecordDao.updateDetail(verification) == 1);
        }
        if (vertifyRecordDao.updateStatus(verification) != 1){
            return false;
        }
        //如果通过了就将该商品信息从审核表中移到在售商品表并将商品加入ES中
        if (!divertVerificationToOnlineProduct(productId)){
            return false;
        }
        return true;
    }

    /**
     * 将商品上架
     * @param productId
     * @return
     */
    @Transactional
    boolean divertVerificationToOnlineProduct(long productId){
        //将商品放入mysql中
        PmsProductVertifyRecordDO verifyRecord = vertifyRecordDao.getVerifyRecordDetail(productId);
        if (verifyRecord == null){
            return false;
        }
        System.out.println(verifyRecord);

        PmsProductOnlineDO onlineDO = transferUtilForProduct.verificationToOnline(verifyRecord);
        System.out.println(onlineDO);
        onlineDao.insert(onlineDO);
        //将商品放入ES中
        ProductEssentialsDTO essential = transferUtilForProduct.transferOnlineToEssential(onlineDO);
        try {
            esUtil.addDoc(ConstanForProduct.ELASTICSEARCH_INDEX, String.valueOf(essential.getProductId()), essential);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
