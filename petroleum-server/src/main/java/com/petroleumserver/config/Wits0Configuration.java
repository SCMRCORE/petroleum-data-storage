package com.petroleumserver.config;

import com.petroleumserver.service.NettyClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 实时数据接口配置
 * netty 客户端配置
 *
 */
@Configuration
@Slf4j
public class Wits0Configuration {

    @Resource
    private NettyClientService nettyClientService;

    /**
     * 返回一个初始化好的client
     */
    @PostConstruct
    public void initializeClient() {
        // 启动netty客户端
        log.info("开始初始化netty客户端....");
        nettyClientService.startClient();
    }
}
