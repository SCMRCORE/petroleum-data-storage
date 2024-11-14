package com.petroleumserver.service.lmp;

import cn.hutool.json.JSONObject;
import com.petroleumcommom.constant.Const;
import com.petroleumcommom.utils.*;
import com.petroleumserver.service.DataLakeService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.KSQLWindow;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class dataLakeServiceImpl implements DataLakeService {

    @Resource
    private HashMap<Integer, String> dataLakeMap;

    @Resource
    private HashMap<Integer, String> apiTokenMap;

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    public String getAppCode(HashMap<String, String> params) throws InterruptedException {
        // TODO 这里是因为公司网络的代理问题，无法连接到服务器的数据库和redis
//         1. 如果当前存有appcode直接取出
        if(Boolean.TRUE.equals(redisTemplate.hasKey(Const.REDIS_KEY_APPCODE))) {
           return (String) redisTemplate.opsForValue().get(Const.REDIS_KEY_APPCODE);
        }
        // 2.没有则重新获取并且刷新缓存，设置redis过期时间为一个半小时（留一点余地）
        //appcode为
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
                appCode = object.getStr("data");
                log.info("获取appCode成功！ {}", appCode);
                redisTemplate.opsForValue().set(Const.REDIS_KEY_APPCODE, appCode, 90, TimeUnit.MINUTES);
//                return appCode;
                // TODO 开发环境硬编码 这里的apiToken是根据datalake大版本更新的时候才会随之改变
                return "152b5e38657e0b8cd73964bc315f74b6";
            } catch (Exception e) {
                log.info("获取appcode请求失败");
                // 睡眠1s重新获k
                Thread.sleep(1000);
                // 更新时间戳的值
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
    public String query(String json, Integer index) throws IOException, InterruptedException {
        // index 判断是哪一张表
        // 优化：使用redis对相同的请求 进行缓存
        String res = getDataFromRedis(json, index);
        if(res != null) { // 判断redis中是否存在数据
            return res;
        } else {
            return fetchData(index, json);
        }
    }


    private String getDataFromRedis(String json, Integer index) {
        String redisKey = index + "_" + json; // 封装redis键
        // 如果存在，那么就返回缓存的值，如果不存在就会直接返回null
        return redisTemplate.opsForValue().get(redisKey);
    }

    /**
     * 直接获得token等头信息，将请求得到的数据封装后返回
     */
    public String fetchData(Integer index, String frontEndJson) throws IOException, InterruptedException {
        String redisKey = index + "_" + frontEndJson; // 封装redis键
        String token = getToken();
//        String appCode = connect();
        // 创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建Post请求
        log.info("当前表的url {}", dataLakeMap.get(index));
        HttpPost postRequest = new HttpPost(dataLakeMap.get(index));
        log.info("创建请求数据湖数据请求成功..... {}", postRequest.toString());
        // 设置请求头
        postRequest.setHeader("token", token);
        // TODO 开发环境：appCode暂时设置为空
        postRequest.setHeader("appCode", "");
        postRequest.setHeader("apiToken", apiTokenMap.get(index));
        postRequest.setHeader("Content-Type", "application/json");
        // 使用原始json
        StringEntity entity = new StringEntity(frontEndJson);
        postRequest.setEntity(entity);

        // 执行请求
        try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
            log.info("响应状态码: {}" , response.getStatusLine().getStatusCode());
            log.info("响应内容: {}",   response.getEntity().getContent());
            // 得到相应内容的字符串, 直接返回给前端
            // 同时将得到的响应内容字符串缓存下来
            setDataInRedis(redisKey, response.getEntity().getContent().toString());
            return response.getEntity().getContent().toString();
        } catch (Exception e) {
            log.info("获取数据失败");
        }
        return null;
    }

    /**
     * 将从数据湖发送过来的数据进行缓存
     * @param redisKey
     */
    private void setDataInRedis(String redisKey, String responseResult) {
        // 设置保存结果数据
        // 设置过期时间为5分钟
        redisTemplate.opsForValue().set(redisKey, responseResult, 5, TimeUnit.MINUTES);
        log.info("redis中缓存结果成功.. 键：{}, 值：{}",redisKey, responseResult);
    }

    private String getToken() {
        return null;
    }
}


