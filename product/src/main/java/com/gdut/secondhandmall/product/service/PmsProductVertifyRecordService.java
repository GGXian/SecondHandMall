package com.gdut.secondhandmall.product.service;

import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.VerificationDTOForProduct;
import com.gdut.secondhandmall.product.model.PmsProductVertifyRecordDO;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-16:24
 * @description
 **/
public interface PmsProductVertifyRecordService {
    int insertNewRecord(PmsProductVertifyRecordDO record);

    List<PmsProductVertifyRecordDO> getVertifyRecords(PageParamDTO pageParam);

    List<PmsProductVertifyRecordDO> getVertifyRecordsForUser(PageParamDTO pageParam, String openId);

    PmsProductVertifyRecordDO getVerifyRecordDetail(long productId);

    boolean completeVerification(VerificationDTOForProduct verification);
}
