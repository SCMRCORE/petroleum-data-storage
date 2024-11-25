package com.petroleumserver;

import com.petroleumcommom.properties.JwtProperties;
import com.petroleumcommom.properties.MinioProperties;
import com.petroleumcommom.properties.NettyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties({MinioProperties.class, JwtProperties.class, NettyProperties.class})
@EnableTransactionManagement // 开启注解方式的事务管理
@SpringBootApplication
@MapperScan("com.petroleumserver.mapper")
public class PetroleumServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetroleumServerApplication.class, args);
    }

}
