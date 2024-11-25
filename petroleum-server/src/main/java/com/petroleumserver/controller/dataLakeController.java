package com.petroleumserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.petroleumcommom.result.Result;
import com.petroleumserver.service.DataLakeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/data")
@Slf4j
public class dataLakeController {

    @Resource
    DataLakeService dataLakeService;

     
    @PostMapping("/connect")
    public Result<Object> connectTest() throws IOException, InterruptedException {
        log.info("开始测测试连接数据湖");
        String appCode = dataLakeService.connect();
        log.info("接收到appcode: {}", appCode);
        return Result.success();
    }

    @GetMapping()
    public String getData() throws IOException, InterruptedException {
        return "hahahah";
    }

     
    @PostMapping("/searchData")
    @ApiOperation("获得数据湖数据")
    public ResponseEntity<String> query(@RequestBody String json, @RequestParam Integer index) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);
        
        if(jsonNode.has("index")) {
            ((ObjectNode)jsonNode).remove("index");
        }
        json = mapper.writeValueAsString(jsonNode);
        log.info("数据湖，请求表：{}, 数据湖请求体：{}", index, json);
        log.info("数据湖得到请求体: {}",  dataLakeService.query(json, index));
        return dataLakeService.query(json, index);
    }
}
