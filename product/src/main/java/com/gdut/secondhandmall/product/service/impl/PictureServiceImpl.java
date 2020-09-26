package com.gdut.secondhandmall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.gdut.secondhandmall.product.dao.PmsProductOnlineDao;
import com.gdut.secondhandmall.product.dto.ProductPictureInUrlDTO;
import com.gdut.secondhandmall.product.model.PmsProductOnlineDO;
import com.gdut.secondhandmall.product.service.PictureService;
import com.gdut.secondhandmall.product.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/13-21:39
 * @description 处理用户上传图片
 **/
@PropertySource("classpath:application.yml")
@Service
public class PictureServiceImpl implements PictureService {
    /**
     * 图片文件夹的父目录
     */
    @Value("${picture.path}")
    private String parentPath;
    @Autowired
    FileUtil fileUtil;
    @Autowired
    PmsProductOnlineDao onlineDao;
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * 将图片存储到本地，并生成url返回
     * @param streamMap
     * @param productId
     * @return
     */
    @Override
    public List<String> storeToLocal(LinkedHashMap<InputStream, String> streamMap, long productId) {
        System.out.println("目录：" + parentPath);
        System.out.println(streamMap);
        List<String> urlList = new ArrayList<>();
        //确保productId目录以及product、comment子目录存在
        String productIdPath = parentPath + FILE_SEPARATOR + productId;
        String productPath = productIdPath + FILE_SEPARATOR + "product";
        String commentPath = productIdPath + FILE_SEPARATOR + "comment";
        if(!fileUtil.makeDirExist(new File(productIdPath))){
            return null;
        }
        if(!fileUtil.makeDirExist(new File(productPath))){
            return null;
        }
        if(!fileUtil.makeDirExist(new File(commentPath))){
            return null;
        }
        //向productId目录中添加图片
        try {
            for(Map.Entry<InputStream, String> entry : streamMap.entrySet()){
                InputStream is = entry.getKey();
                String suffix = entry.getValue();
                String uuid = UUID.randomUUID().toString();
                String file = productPath + FILE_SEPARATOR + uuid.substring(uuid.lastIndexOf('-') + 1) + suffix;
                //读取InputStream的内容到数组中
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                //将数组中的内容输出到文件中
                File newFile = new File(file);
                if(!fileUtil.makeFileExist(newFile)){
                    return null;
                }
                OutputStream os = new FileOutputStream(newFile);
                os.write(buffer);
                urlList.add(file);
                System.out.println(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(urlList);
        return urlList;
    }

    /**
     * 删除图片
     * @param pictures
     * @param productId
     * @return
     */
    @Override
    public boolean deletePictures(List<String> pictures, long productId) {
        if (!deleteFromLocal(pictures, productId)){
            return false;
        }
        if (!deleteFromDatabase(pictures, productId)){
            return false;
        }
        return true;
    }

    /**
     * 删除本地存储的图片
     * @param pictures
     * @param productId
     * @return
     */
    private boolean deleteFromLocal(List<String> pictures, long productId) {
        File file = null;
        for (String picture : pictures) {
            file = new File(picture);
            if (file.isDirectory() || !file.exists()){
                return false;
            }
            file.delete();
        }
        return true;
    }

    /**
     * 获取本地图片的url
     * @param productId
     * @return
     */
    @Override
    public List<String> getFilesPath(long productId){
        String productPath = parentPath + FILE_SEPARATOR + productId + FILE_SEPARATOR + "product";
        List<String> picList = new ArrayList<>();
        File productDir = new File(productPath);
        File[] picPath = productDir.listFiles();
        for (File file : picPath) {
            picList.add(file.getAbsolutePath());
        }
        return picList;
    }

    /**
     * 从数据库中删除图片
     * @param pictures
     * @param productId
     * @return
     */
    private boolean deleteFromDatabase(List<String> pictures, long productId) {
        PmsProductOnlineDO product = onlineDao.selectByProductId(productId);
        ProductPictureInUrlDTO picUrl = JSON.parseObject(product.getPicUrl(), ProductPictureInUrlDTO.class);
        List<String> detail = picUrl.getDetail();
        //若要删除主图则将主图对应的url置为null
        if (pictures.contains(picUrl.getMain())){
            picUrl.setMain(null);
        }
        for (String picture : detail) {
            if (pictures.contains(picture)){
                detail.remove(picture);
            }
        }
        picUrl.setDetail(detail);
        product.setPicUrl(JSON.toJSONString(picUrl));
        onlineDao.updateByProductId(product);
        return true;
    }
}
