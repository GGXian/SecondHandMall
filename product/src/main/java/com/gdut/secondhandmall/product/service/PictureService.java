package com.gdut.secondhandmall.product.service;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/13-21:42
 * @description
 **/
public interface PictureService {
    List<String> storeToLocal(LinkedHashMap<InputStream, String> streamMap, long productId);

    boolean deletePictures(List<String> pictures, long productId);

    List<String> getFilesPath(long productId);
}
