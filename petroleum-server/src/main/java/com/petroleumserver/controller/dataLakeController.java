package com.petroleumserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.petroleumcommom.result.Result;
import com.petroleumserver.service.DataLakeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/data")
@Slf4j
public class dataLakeController {

    @Resource
    DataLakeService dataLakeService;

    /**
     * TODO: 当场线下测试数据湖连接
     * 测试连接数据湖
     */
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

    /**
     * 对数据进行查询,之后直接返回到前端查询内容
     * 使用map接收前端的原始json格式
     */
    @PostMapping("/searchData")
    @ApiOperation("获得数据湖数据")
    public String  query(@RequestBody String json, @RequestParam Integer index) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);
        // 去掉index键
        if(jsonNode.has("index")) {
            ((ObjectNode)jsonNode).remove("index");
        }
        json = mapper.writeValueAsString(jsonNode);
        log.info("数据湖，请求表：{}, 数据湖请求体：{}", index, json);
        return dataLakeService.query(json, index);
    }
}
