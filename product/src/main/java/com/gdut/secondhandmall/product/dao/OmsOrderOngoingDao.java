package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.model.OmsOrderOngoingDO;
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
public interface OmsOrderOngoingDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderOngoingDO record);

    OmsOrderOngoingDO selectByPrimaryKey(Long id);

    OmsOrderOngoingDO selectByOrderId(Long orderId);

    List<OmsOrderOngoingDO> selectAll();

    int updateByPrimaryKey(OmsOrderOngoingDO record);

    List<OmsOrderOngoingDO> getAllUnfinishedOrdersForBuyer(String openId);

    List<OmsOrderOngoingDO> getAllUnfinishedOrdersForseller(String openId);

    int deleteByOrderId(Long orderId);
}