package com.petroleumserver.config;

import com.petroleumcommom.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class MinioConfiguration {
    @Resource
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient =  MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        log.info("MinioClient initialized: accessKey={}, secretKey={}", minioProperties.getAccessKey(),
                minioProperties.getSecretKey());
        log.info("minioClient初始化完毕,{}", minioClient);
        return minioClient;
    }
}
