package com.gdut.secondhandmall.product.dao;

import com.gdut.secondhandmall.product.model.PmsProductCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/07
* @description 针对商品目录表的CRUD操作
*/
@Mapper
@Repository
public interface PmsProductCategoryDao {
    /**
     * 根据目录id删除目录
     * @param id
     * @return 1为成功，0为失败
     */
    int deleteByPrimaryKey(Short id);

    /**
     * 增加商品目录
     * @param record
     * @return 1为成功，0为失败
     */
    int insert(PmsProductCategoryDO record);

    /**
     * 根据目录id查询目录
     * @param id
     * @return 商品目录
     */
    PmsProductCategoryDO selectByPrimaryKey(Short id);

    /**
     * 查询全部目录
     * @return 目录列表
     */
    List<PmsProductCategoryDO> selectAll();

    /**
     * 根据目录id更新目录
     * @param record
     * @return 1为成功，0为失败
     */
    int updateByPrimaryKey(PmsProductCategoryDO record);

    /**
     * 根据一级目录获取二级目录
     * @param primaryDirectory
     * @return
     */
    List<PmsProductCategoryDO> getSecondaryDirectoryByPrimaryDirectory(String primaryDirectory);
}