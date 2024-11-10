package com.petroleumserver.service.lmp;

import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.utils.*;
import com.petroleumpojo.dto.*;
import com.petroleumpojo.entity.FuZa;
import com.petroleumpojo.entity.JiBen;
import com.petroleumpojo.entity.JingShen;
import com.petroleumpojo.entity.ZuanTou;
import com.petroleumserver.mapper.petroleumMapper;
import com.petroleumserver.service.dataLakeService;
import com.petroleumserver.service.petroleumService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class dataLakeServicelmp implements dataLakeService {
    @Override
    public void connect() throws IOException {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("appName", "DApi");
        paramMap.put("appId", "880CADF172AC4ABD8864440804EE216F");
        paramMap.put("timeStamp",(System.currentTimeMillis()+"").substring(0, 8));
        try{
            String appCode = HttpClientUtil.doPost("http://10.77.78.223/apigateway/appauth/getappid", paramMap);
            log.info("获取成功:{}", appCode);
        }catch (Exception e){
            log.info("获取失败");
            e.printStackTrace();
        }


        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建请求对象
        HttpPost httpPost = new HttpPost("http://10.77.78.223/apigateway/appauth/getappid");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appName","DApi");
        jsonObject.put("appId","880CADF172AC4ABD8864440804EE216F");
        jsonObject.put("timeStamp",(System.currentTimeMillis()+"").substring(0, 8));
        StringEntity entity = new StringEntity(jsonObject.toString());
        //指定编码方式
        entity.setContentEncoding("utf-8");
        //数据格式
        entity.setContentType("application/json");
//        post请求设置请求参数（JSON格式）
        httpPost.setEntity(entity);
        //发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //解析返回结果
        //获取服务端返回回来的状态码
        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("服务端返回的状态码: " +statusCode);

        //获取服务端返回回来的响应体，然后通过一个工具类来解析这个响应体
        HttpEntity entity1 = response.getEntity();
        String body = EntityUtils.toString(entity1);

        System.out.println("服务端返回的数据是: " +body);

        //关闭资源
        response.close();
        httpClient.close();

        //创建httpclient对象
        CloseableHttpClient httpClients = HttpClients.createDefault();

        //创建请求对象
        HttpGet httpGet = new HttpGet("http://10.77.78.223/apigateway/appauth/getappid?appName=DApi&appId=880CADF172AC4ABD8864440804EE216F&timeStamp="+(System.currentTimeMillis()+"").substring(0, 8));

        //发送请求对象,并接受响应结果
        CloseableHttpResponse response = httpClients.execute(httpGet);

        //获取服务端返回回来的状态码
        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("服务端返回的状态码: " +statusCode);

        //获取服务端返回回来的响应体，然后通过一个工具类来解析这个响应体
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);

        System.out.println("服务端返回的数据是: " +body);

        response.close();
        httpClients.close();
    }
}


