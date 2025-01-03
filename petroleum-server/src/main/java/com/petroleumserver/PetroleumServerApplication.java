package com.petroleumserver;

import com.petroleumcommom.properties.MinioProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableConfigurationProperties(MinioProperties.class)
@EnableTransactionManagement // 开启注解方式的事务管理
@MapperScan("com.petroleumserver.mapper")
public class PetroleumServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetroleumServerApplication.class, args);
    }

}
