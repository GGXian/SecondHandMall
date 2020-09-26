package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.model.OmsOrderCompletedDO;
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
public interface OmsOrderCompletedDao{
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderCompletedDO record);

    OmsOrderCompletedDO selectByPrimaryKey(Long id);

    List<OmsOrderCompletedDO> selectAll();

    int updateByPrimaryKey(OmsOrderCompletedDO record);

    List<OmsOrderCompletedDO> getOrderForBuyer(String openId);

    List<OmsOrderCompletedDO> getOrderForSeller(String openId);
}