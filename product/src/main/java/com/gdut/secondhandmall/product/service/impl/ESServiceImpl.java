package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.dto.SortParamDTO;
import com.gdut.secondhandmall.product.service.ESService;
import com.gdut.secondhandmall.product.util.ConstanForProduct;
import com.gdut.secondhandmall.product.util.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/16-17:34
 * @description 处理ElasticSearch数据相关的业务逻辑
 **/
@Service
public class ESServiceImpl implements ESService {
    @Autowired
    ESUtil esUtil;

    /**
     * 增加新商品信息
     * @param essential
     * @return
     */
    @Override
    public boolean put(ProductEssentialsDTO essential) {
        try {
            esUtil.addDoc(ConstanForProduct.ELASTICSEARCH_INDEX, String.valueOf(essential.getProductId()), essential);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 批量增加新商品信息
     * @param essentials
     * @return
     */
    @Override
    public boolean put(List<ProductEssentialsDTO> essentials) {
        try {
            esUtil.bulkAdd(ConstanForProduct.ELASTICSEARCH_INDEX, essentials);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除商品信息
     * @param essential
     * @return
     */
    @Override
    public boolean delete(ProductEssentialsDTO essential) {
        try {
            esUtil.deleteDoc(ConstanForProduct.ELASTICSEARCH_INDEX, String.valueOf(essential.getProductId()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新商品信息
     * @param essential
     * @return
     */
    @Override
    public boolean update(ProductEssentialsDTO essential) {
        try {
            esUtil.updateDoc(ConstanForProduct.ELASTICSEARCH_INDEX, String.valueOf(essential.getProductId()), essential);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据关键词搜索商品，并将结果集排序
     * @param key
     * @param sortParam
     * @param pageParam
     * @return
     */
    @Override
    public List<ProductEssentialsDTO> search(String key, SortParamDTO sortParam, PageParamDTO pageParam) {
        List<ProductEssentialsDTO> list = null;
        int from = (pageParam.getPage() - 1) * pageParam.getSize();
        int size = pageParam.getSize();
        try {
            list = esUtil.searchForNameAndDescription(ConstanForProduct.ELASTICSEARCH_INDEX, key, sortParam.getSort(),
                    sortParam.getOrder(), from, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据二级商品目录搜索商品，并将结果集排序
     * @param tag
     * @param sortParam
     * @param pageParam
     * @return
     */
    @Override
    public List<ProductEssentialsDTO> getByTag(short tag, SortParamDTO sortParam, PageParamDTO pageParam) {
        List<ProductEssentialsDTO> list = null;
        int from = (pageParam.getPage() - 1) * pageParam.getSize();
        int size = pageParam.getSize();
        try {
            list = esUtil.searchByTag(ConstanForProduct.ELASTICSEARCH_INDEX, tag, sortParam.getSort(),
                    sortParam.getOrder(),
                    from, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
