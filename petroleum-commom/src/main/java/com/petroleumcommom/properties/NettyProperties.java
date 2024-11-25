package com.petroleumcommom.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("netty")
@Component
@Data
public class NettyProperties {
    private String host;
    private int port;
    private int MAX_RETRY;
    private String username;
    private String password;
}
