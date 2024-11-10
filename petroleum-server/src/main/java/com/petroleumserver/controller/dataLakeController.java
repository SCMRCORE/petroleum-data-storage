package com.petroleumserver.controller;

import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.DataLakeDTO;
import com.petroleumpojo.dto.DataLakeSearchPageDTO;
import com.petroleumpojo.entity.DataLake;
import com.petroleumpojo.vo.DataLakeVO;
import com.petroleumserver.service.dataLakeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/data")
@Slf4j
public class dataLakeController {

    @Resource
    dataLakeService dataLakeService;

    /**
     * TODO: 当场线下测试数据湖连接
     * 测试连接数据湖
     */
    @PostMapping("/connect")
    public Result<Object> connectTest() throws IOException, InterruptedException {
        String appCode = dataLakeService.connect();
        log.info("接收到appcode: {}", appCode);
        return Result.success();
    }

    /**
     * 对数据进行查询,之后直接返回到前端查询内容
     * 使用map接收前端的原始json格式
     */
    @PostMapping("/searchData")
    @ApiOperation("获得数据湖数据")
    public String  query(@RequestBody String json) throws IOException, InterruptedException {
        log.info("数据湖请求体：{}", json);
        return dataLakeService.query(json);
    }
}
