package com.petroleumserver.config;

import com.petroleumcommom.constant.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 实现自己定义一个diy的configuration，返回自己定义的bean
 */
@Configuration
public class DiyConfiguration {

    /**
     *
     * 作为返回数据湖访问接口map
     * @return
     */
    @Bean
    public HashMap<Integer, String> generateDataLakeMap() {
        HashMap<Integer, String> datalakeMap = new HashMap<>();
        datalakeMap.put(1, Const.DATALAKE_GET_TABLE_1);
        datalakeMap.put(2, Const.DATALAKE_GET_TABLE_2);
        datalakeMap.put(3, Const.DATALAKE_GET_TABLE_3);
        datalakeMap.put(4, Const.DATALAKE_GET_TABLE_4);
        datalakeMap.put(5, Const.DATALAKE_GET_TABLE_5);
        datalakeMap.put(6, Const.DATALAKE_GET_TABLE_6);
        datalakeMap.put(7, Const.DATALAKE_GET_TABLE_7);
        return datalakeMap;
    }

    /**
     * 获取每一个接口所对应的链接的apiToken
     * @return
     */
    @Bean
    public HashMap<Integer, String> getApiTokenMap() {
        HashMap<Integer, String> apiTokenMap = new HashMap<>();
        apiTokenMap.put(1, Const.DATALAKE_APITOKEN_1);
        apiTokenMap.put(2, Const.DATALAKE_APITOKEN_2);
        apiTokenMap.put(3, Const.DATALAKE_APITOKEN_3);
        apiTokenMap.put(4, Const.DATALAKE_APITOKEN_4);
        apiTokenMap.put(5, Const.DATALAKE_APITOKEN_5);
        apiTokenMap.put(6, Const.DATALAKE_APITOKEN_6);
        apiTokenMap.put(7, Const.DATALAKE_APITOKEN_7);
        return apiTokenMap;
    }

}
