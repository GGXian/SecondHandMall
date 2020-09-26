package com.gdut.secondhandmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/15-14:18
 * @description 管理员的审核记录模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationDTOForOrder {
    private long orderId;
    private long adminId;
    private String isPassed;
    private int status;
    private String feedback;
    private Date handleTime;
}
