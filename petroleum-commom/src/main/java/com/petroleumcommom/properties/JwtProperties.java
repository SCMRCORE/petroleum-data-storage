package com.petroleumcommom.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties("petroleum.jwt")
@Component
public class JwtProperties {
     
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
}
