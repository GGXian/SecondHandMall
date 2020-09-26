package com.gdut.secondhandmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/25-10:29
 * @description 商品图片的文件模型
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPictureInFileDTO {
    /**
     * 商品主图
     */
    private MultipartFile main;
    /**
     * 商品细节图
     */
    private List<MultipartFile> detail;
}
