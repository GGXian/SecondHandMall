package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.model.PmsProductOffshelvesDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/11
* @description 
*/
@Mapper
@Repository
public interface PmsProductOffshelvesDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductOffshelvesDO record);

    PmsProductOffshelvesDO selectByPrimaryKey(Long id);

    List<PmsProductOffshelvesDO> selectAll();

    int updateByPrimaryKey(PmsProductOffshelvesDO record);
}