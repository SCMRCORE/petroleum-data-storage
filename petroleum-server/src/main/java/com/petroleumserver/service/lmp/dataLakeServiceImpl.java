package com.petroleumserver.service.lmp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petroleumcommom.constant.Const;
import com.petroleumcommom.utils.*;
import com.petroleumserver.service.DataLakeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class dataLakeServiceImpl implements DataLakeService {

    @Resource
    private HashMap<Integer, String> dataLakeMap;

    @Resource
    private HashMap<Integer, Object> apiTokenMap;

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    public String getAppCode(HashMap<String, String> params) throws InterruptedException {
         
        if(Boolean.TRUE.equals(redisTemplate.hasKey(Const.REDIS_KEY_APPCODE))) {
           return (String) redisTemplate.opsForValue().get(Const.REDIS_KEY_APPCODE);
        }
        
        
        String appCode = "";
        while (appCode.isEmpty()) {
            try {
               String response= HttpClientUtil.doGet(Const.DATALAKE_GET_APPCODE_URL, params);
                JSONObject object = new JSONObject(response);
                Integer code = object.getInt("code");
                if(code != 200) {
                    log.info("请求得到code失败，响应体如下：{}", object);
                    Thread.sleep(1000);
                    log.info("重新获取appcode...");
                    continue;
                } 
                log.info("获取appCode成功！ {}", appCode);
                redisTemplate.opsForValue().set(Const.REDIS_KEY_APPCODE, appCode, 90, TimeUnit.MINUTES); 
                
                return "152b5e38657e0b8cd73964bc315f74b6";
            } catch (Exception e) {
                log.info("获取appcode请求失败");
                
                Thread.sleep(1000);
                
                params.put("timeStamp",(System.currentTimeMillis()+"").substring(0, 8));
                log.info("再次重新获取");
            }
        }
        return null;
    }


    @Override
    public String connect() throws IOException, InterruptedException {
        String appCode = "";
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("appName", "DApi");
        paramMap.put("appId", "880CADF172AC4ABD8864440804EE216F");
        paramMap.put("timeStamp",(System.currentTimeMillis()+"").substring(0, 8));
        log.info("请求头如下：{}", paramMap);
        appCode = getAppCode(paramMap);


        return appCode;
    }


    @Override
    public ResponseEntity<String> query(String json, Integer index) throws IOException, InterruptedException {
        
         
        String res = null;   
            return fetchData(index, json); 
    }


    private String getDataFromRedis(String json, Integer index) {
        String redisKey = index + "_" + json; 
        
        return redisTemplate.opsForValue().get(redisKey);
    }

     
    public ResponseEntity<String> fetchData(Integer index, String frontEndJson) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String redisKey = index + "_" + frontEndJson; 
        String token = getToken(); 
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        log.info("当前表的url {}", dataLakeMap.get(index));
        HttpPost postRequest = new HttpPost(dataLakeMap.get(index));
        log.info("创建请求数据湖数据请求..... {}", postRequest.toString());
        
        
        
        StringEntity entity = new StringEntity(frontEndJson);
        postRequest.setEntity(entity);
        postRequest.setHeader("token", token);
        postRequest.setHeader("appCode", "152b5e38657e0b8cd73964bc315f74b6");
        postRequest.setHeader("apiToken", (String) apiTokenMap.get(index));
        log.info(apiTokenMap.get(index).toString());
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("Accept", "* 
    private void setDataInRedis(String redisKey, String responseResult) {
        
        
        redisTemplate.opsForValue().set(redisKey, responseResult, 5, TimeUnit.MINUTES);
        log.info("redis中缓存结果成功.. 键：{}, 值：{}",redisKey, responseResult);
    }

    private String getToken() {
        return null;
    }
}


