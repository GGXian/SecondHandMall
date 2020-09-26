package com.gdut.secondhandmall.product.service;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-16:38
 * @description
 **/
public interface PmsProductCategoryService {
    List<String> getSecondaryDirectoryByPrimaryDirectory(String primaryDirectory);
}
