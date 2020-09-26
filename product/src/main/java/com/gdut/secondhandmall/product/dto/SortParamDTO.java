package com.gdut.secondhandmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/16-17:49
 * @description 排序的参数模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortParamDTO {
    private String sort;
    private String order;
}
