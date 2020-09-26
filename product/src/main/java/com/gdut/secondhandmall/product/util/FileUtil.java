package com.gdut.secondhandmall.product.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/14-14:33
 * @description 文件处理工具类(让使用者来保证上一级目录存在)
 **/
@Component
public class FileUtil {
    /**
     * 确保文件在系统中存在
     * @param file
     * @return
     */
    public boolean makeFileExist(File file){
        boolean result = false;
        if(file.exists()){
            return true;
        }
        try {
            result = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 确保文件夹在系统中存在
     * @param file
     * @param file
     * @return
     */
    public boolean makeDirExist(File file){
        if(file.exists()){
            return true;
        }
        return file.mkdir();
    }

    /**
     * 清除url中转义字符前的\
     * @param url
     * @return
     */
    public String escapeCharacter(String url){
        int count = 1;
        String[] urls = url.split("\\\\");
        StringBuilder newUrl = new StringBuilder(urls[0]);
        for (; count < urls.length; count++){
            newUrl.append("\\" + urls[count]);
        }
        return newUrl.toString();
    }
}
