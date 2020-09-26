package com.gdut.secondhandmall.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/05
* @description 在售商品模型
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "在售商品模型")
public class PmsProductOnlineDO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private String openid;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品名")
    private String productName;

    @ApiModelProperty(value = "商品目录id")
    private Short directoryId;

    @ApiModelProperty(value = "商品图片url")
    private String picUrl;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "浏览人数")
    private Short visitor;

    @ApiModelProperty(value = "发布时间")
    private Date time;
}