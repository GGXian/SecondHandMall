package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.dto.VerificationDTOForOrder;
import com.gdut.secondhandmall.product.model.OmsOrderReturnApplyDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/05
* @description 
*/
@Mapper
@Repository
public interface OmsOrderReturnApplyDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnApplyDO record);

    OmsOrderReturnApplyDO selectByPrimaryKey(Long id);

    List<OmsOrderReturnApplyDO> selectAll();

    int updateByPrimaryKey(OmsOrderReturnApplyDO record);

    List<OmsOrderReturnApplyDO> selectByOpenId(String openId);

    int updateDetail(VerificationDTOForOrder verification);

    OmsOrderReturnApplyDO selectByOrderId(long orderId);
}