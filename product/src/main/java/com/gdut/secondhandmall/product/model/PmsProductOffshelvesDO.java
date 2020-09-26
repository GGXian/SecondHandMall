package com.gdut.secondhandmall.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/11
* @description 已下架商品模型
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "已下架商品模型")
public class PmsProductOffshelvesDO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private String openid;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "下架原因")
    private String reason;

    @ApiModelProperty(value = "审核人")
    private Long verifyMan;

    @ApiModelProperty(value = "下架时间")
    private Date time;
}