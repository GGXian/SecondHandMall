import com.alibaba.fastjson.JSON;
import com.gdut.secondhandmall.product.dto.ProductPictureInUrlDTO;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/11-11:20
 * @description
 **/

public class test2 {
    @Test
    public void test(){
//        System.out.println(TimeUtilForProduct.getTimeForEs().replace("-", ""));
        ProductPictureInUrlDTO productPictureDTO = new ProductPictureInUrlDTO();
        productPictureDTO.setMain("1");
        productPictureDTO.setDetail(Arrays.asList(new String[]{"2", "3", "4"}.clone()));
        String a = JSON.toJSONString(productPictureDTO);
        ProductPictureInUrlDTO x = JSON.parseObject("{\"detail\":[\"2\",\"3\",\"4\"],\"main\":\"1\"}",
                ProductPictureInUrlDTO.class);
        System.out.println(a);
    }
}
