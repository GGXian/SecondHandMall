package com.gdut.secondhandmall.product.dto;

import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/25-11:06
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModificationDTO implements Serializable {
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

    private String picturesToUpload;

    private String picturesToDelete;
}
