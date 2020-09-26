package com.gdut.secondhandmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/27-15:30
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortAndPageParamDTO {
    private String sort;
    private String order;
    private int page;
    private int size;
}
