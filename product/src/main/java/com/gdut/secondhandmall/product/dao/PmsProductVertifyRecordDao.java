package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.dto.VerificationDTOForProduct;
import com.gdut.secondhandmall.product.model.PmsProductVertifyRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/07
* @description 针对商品审核表的CRUD操作
*/
@Mapper
@Repository
public interface PmsProductVertifyRecordDao {
    /**
     * 根据记录id删除商品审核记录
     * @param id
     * @return 1为成功，0为失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入商品审核记录
     * @param record
     * @return 1为成功，0为失败
     */
    int insert(PmsProductVertifyRecordDO record);

    /**
     * 根据记录id获取商品审核记录
     * @param id
     * @return 审核记录
     */
    PmsProductVertifyRecordDO selectByPrimaryKey(Long id);

    /**
     * 根据openID获取用户的商品审核记录
     * @param openID
     * @return 审核记录
     */
    List<PmsProductVertifyRecordDO> selectByOpenID(String openID);

    /**
     * 获取全部商品审核记录
     * @return 商品审核记录列表
     */
    List<PmsProductVertifyRecordDO> selectAll();

    /**
     * 根据记录id修改商品审核记录
     * @param record
     * @return 1为成功，0为失败
     */
    int updateByPrimaryKey(PmsProductVertifyRecordDO record);

    /**
     * 根据商品id获取商品审核记录
     * @param productId
     * @return
     */
    PmsProductVertifyRecordDO getVerifyRecordDetail(long productId);

    /**
     * 根据商品id更新对发布商品的反馈
     * @param verification
     * @return
     */
    int updateDetail(VerificationDTOForProduct verification);

    int updateStatus(VerificationDTOForProduct verification);
}