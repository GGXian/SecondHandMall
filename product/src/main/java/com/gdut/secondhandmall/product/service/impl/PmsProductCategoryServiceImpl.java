package com.gdut.secondhandmall.product.service.impl;

import com.gdut.secondhandmall.product.dao.PmsProductCategoryDao;
import com.gdut.secondhandmall.product.model.PmsProductCategoryDO;
import com.gdut.secondhandmall.product.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-16:40
 * @description 处理商品目录信息的相关逻辑
 **/
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Autowired
    PmsProductCategoryDao categoryDao;

    /**
     * 获取一级目录下的二级目录
     * @param primaryDirectory
     * @return
     */
    @Override
    public List<String> getSecondaryDirectoryByPrimaryDirectory(String primaryDirectory) {
        List<String> sList = new ArrayList<>();
        List<PmsProductCategoryDO> list = categoryDao.getSecondaryDirectoryByPrimaryDirectory(primaryDirectory);
        for (PmsProductCategoryDO pmsProductCategoryDO : list) {
            sList.add(pmsProductCategoryDO.getSecondaryDirectory());
        }
        return sList;
    }
}
