package cn.demo.springsrcurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("cn.demo.springsrcurity.Mapper")
public class SpringsrcurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsrcurityApplication.class, args);
    }

}
