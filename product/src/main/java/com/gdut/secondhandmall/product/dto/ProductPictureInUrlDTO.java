package com.gdut.secondhandmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/24-22:47
 * @description 商品图片的url模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPictureInUrlDTO implements Serializable {
    /**
     * 商品主图
     */
    private String main;
    /**
     * 商品细节图
     */
    private List<String> detail;
}
