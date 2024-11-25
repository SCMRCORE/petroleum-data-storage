package com.petroleumserver.config;

import cn.hutool.http.webservice.SoapClient;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapClientConfig {


    /**
     * 返回将xml与java对象进行序列化的marshaller
     * @return
     */
    @Bean
    Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths(
                "com.petroleumpojo.witsml.element",
                "com.petroleumpojo.witsml"
        );
        return marshaller;
    }

    @Bean
    WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        return new WebServiceTemplate(marshaller);
    }


}
