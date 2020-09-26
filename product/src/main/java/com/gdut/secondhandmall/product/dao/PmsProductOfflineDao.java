package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.model.PmsProductOfflineDO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/07
* @description 针对已出售商品表的CRUD操作
*/
@Mapper
@Repository
public interface PmsProductOfflineDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductOfflineDO record);

    PmsProductOfflineDO selectByPrimaryKey(Long id);

    List<PmsProductOfflineDO> selectAll();

    int updateByPrimaryKey(PmsProductOfflineDO record);

    List<PmsProductOfflineDO> getProductsSoldForUser(String openId);

    PmsProductOfflineDO selectByProductId(Long productId);
}