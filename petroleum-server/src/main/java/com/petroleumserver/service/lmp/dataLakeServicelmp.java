package com.petroleumserver.service.lmp;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petroleumcommom.constant.Const;
import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.utils.*;
import com.petroleumpojo.dto.DataLakeDTO;
import com.petroleumpojo.dto.DataLakeSearchPageDTO;
import com.petroleumserver.service.dataLakeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.HashMap;

@Service
@Slf4j
public class dataLakeServicelmp implements dataLakeService {


    @Override
    public String connect() throws IOException {
        String appCode = "";
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("appName", "DApi");
        paramMap.put("appId", "880CADF172AC4ABD8864440804EE216F");
        paramMap.put("timeStamp",(System.currentTimeMillis()+"").substring(0, 8));
        // TODO: 增加失败重试机制 使用缓存缓存，appcode的有效时间为2hour
        while(appCode.isEmpty()) {
            try{
                appCode = HttpClientUtil.doPost(Const.DATALAKE_GET_APPCODE_URL, paramMap);
                log.info("获取成功:{}", appCode);
            }catch (Exception e){
                log.info("获取失败");
                log.info("再次重新获取");
            }
        }
        return appCode;
    }

    public String getAppCode() throws IOException {
        return connect();
    }

    /**
     * 返回一个header等已经封装好的client类
     */
    public HttpClient getClient(DataLakeSearchPageDTO requestBody) throws IOException {
        // TODO: apiToken 和 token都等待获取
        String token = getToken();
        String appCode = connect();
        String apiToken = "";
        // 创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建Post请求
        HttpPost postRequest = new HttpPost(Const.DATALAKE_GET_APPCODE_URL);

        // 设置请求头
        postRequest.setHeader("token", token);
        postRequest.setHeader("appCode", appCode);
        postRequest.setHeader("apiToken", apiToken);
        postRequest.setHeader("Content-Type", "application/json");


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestBody);

        // 设置请求体
        StringEntity requestEntity = new StringEntity(json, "UTF-8");
        postRequest.setEntity(requestEntity);

        try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
            log.info("响应状态码: {}" , response.getStatusLine().getStatusCode());
            log.info("响应内容: {}",   response.getEntity().getContent());
        }
    }

    private String getToken() {
        return null;
    }

    /**
     * 查询获得appcode 以及token，将得到数据返回
     * @return
     */
    @Override
    public PageResult query(DataLakeSearchPageDTO dto) {
        return new PageResult();
    }
}


