package cn.demo.springsrcurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@MapperScan("cn.demo.springsrcurity.Mapper")
@EnableGlobalMethodSecurity(securedEnabled=true) //开启权限控制
public class SpringsrcurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsrcurityApplication.class, args);
    }

}
