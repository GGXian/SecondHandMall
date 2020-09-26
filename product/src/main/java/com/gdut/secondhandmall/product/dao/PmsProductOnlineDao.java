package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/07
* @description 针对在售商品表的CRUD操作
*/
@Mapper
@Repository
public interface PmsProductOnlineDao {
    /**
     * 根据主键删除商品信息
     * @param id
     * @return 1为成功，0为失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 增加商品信息
     * @param record
     * @return 1为成功，0为失败
     */
    int insert(PmsProductOnlineDO record);

    /**
     * 根据主键获取商品详细信息
     * @param id
     * @return 根据主键返回商品详细信息
     */
    PmsProductOnlineDO selectByPrimaryKey(Long id);

    /**
     * 获取全部商品信息
     * @return 商品列表
     */
    List<PmsProductOnlineDO> selectAll();

    /**
     * 根据商品id更改商品信息
     * @param record
     * @return 1为成功，0为失败
     */
    int updateByPrimaryKey(PmsProductOnlineDO record);

    /**
     * 查询所有商品的概要信息
     * @return 商品概要信息列表
     */
    List<ProductEssentialsDTO> selectessentials();

    /**
     * 查询浏览人数最多的20个商品
     * @return
     */
    List<ProductEssentialsDTO> selectessentialsSortedByVisitors();

    /**
     * 根据商品id查询商品信息
     * @param productId
     * @return
     */
    PmsProductOnlineDO selectByProductId(Long productId);

    List<PmsProductOnlineDO> getProductsForSaleForUser(String openId);

    int updateVisitors(long productId);

    int updateByProductId(PmsProductOnlineDO record);
}