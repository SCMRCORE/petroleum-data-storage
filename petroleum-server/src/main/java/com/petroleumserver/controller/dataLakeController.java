package com.petroleumserver.controller;

import com.petroleumcommom.result.Result;
import com.petroleumserver.service.dataLakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/data")
@Slf4j
public class dataLakeController {

    @Resource
    dataLakeService dataLakeService;

    /**
     * 测试连接数据湖
     * @return
     */
    @PostMapping("/connect")
    public Result connectTest() throws IOException {
        dataLakeService.connect();
        return Result.success();
    }


}
