package com.petroleumserver.config;

import com.petroleumserver.service.NettyClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

 
@Configuration
@Slf4j
public class Wits0Configuration {

    @Resource
    private NettyClientService nettyClientService;

     
    @PostConstruct
    public void initializeClient() {
        
        log.info("开始初始化netty客户端....");
        nettyClientService.startClient();
    }
}
