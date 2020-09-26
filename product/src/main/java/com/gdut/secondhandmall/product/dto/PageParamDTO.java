package com.gdut.secondhandmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-21:18
 * @description 分页参数模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParamDTO {
    private int page;
    private int size;
}
