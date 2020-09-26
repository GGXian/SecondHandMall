package com.gdut.secondhandmall.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* @author GGXian
* @project secondhandmall
* @createTIme 2020/08/05
* @description 商品退货申请记录
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "商品退货申请记录")
public class OmsOrderReturnApplyDO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private Long orderId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "买家id")
    private String buyerId;

    @ApiModelProperty(value = "卖家id")
    private String sellerId;

    @ApiModelProperty(value = "申请时间")
    private Date createTime;

    @ApiModelProperty(value = "申请原因")
    private String reason;

    @ApiModelProperty(value = "处理状态")
    private int status;

    @ApiModelProperty(value = "处理人员")
    private Long handleMan;

    @ApiModelProperty(value = "处理备注")
    private String handleNote;

    @ApiModelProperty(value = "处理时间")
    private Date handleTime;
}