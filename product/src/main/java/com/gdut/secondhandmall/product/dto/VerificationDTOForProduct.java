package com.gdut.secondhandmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/15-14:18
 * @description 管理员的审核记录模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationDTOForProduct {
    private long productId;
    private long adminId;
    private String isPassed;
    private String feedback;
}
