import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTIme 2020/8/7-15:29
 * @description
 **/
@RestController
public class test {
    @Autowired
    DataSource dataSource;
    @RequestMapping("/test")
    public void test(){
        DruidDataSource druidDataSource = (DruidDataSource) this.dataSource;
        System.out.println(druidDataSource.getName());
    }
}
