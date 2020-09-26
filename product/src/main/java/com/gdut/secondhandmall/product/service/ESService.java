package com.gdut.secondhandmall.product.service;

import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.ProductEssentialsDTO;
import com.gdut.secondhandmall.product.dto.SortParamDTO;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/15-17:13
 * @description
 **/
public interface ESService {
    boolean put(ProductEssentialsDTO essential);

    boolean put(List<ProductEssentialsDTO> essentials);

    boolean delete(ProductEssentialsDTO essential);

    boolean update(ProductEssentialsDTO essential);

    List<ProductEssentialsDTO> search(String key, SortParamDTO sortParam, PageParamDTO pageParam);

    List<ProductEssentialsDTO> getByTag(short tag, SortParamDTO sortParam, PageParamDTO pageParam);
}
