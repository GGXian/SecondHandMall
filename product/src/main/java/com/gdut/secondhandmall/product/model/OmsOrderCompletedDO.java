package com.gdut.secondhandmall.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/05
* @description 已完成订单模型
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "已完成订单模型")
public class OmsOrderCompletedDO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private Long orderId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "支付方式")
    private Byte payType;

    @ApiModelProperty(value = "买家id")
    private String buyerId;

    @ApiModelProperty(value = "卖家id")
    private String sellerId;

    @ApiModelProperty(value = "订单完成时间")
    private Date time;
}