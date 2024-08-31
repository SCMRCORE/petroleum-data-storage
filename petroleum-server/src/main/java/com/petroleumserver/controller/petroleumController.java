package com.petroleumserver.controller;

import com.petroleumcommom.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/petroleum")
@Slf4j
public class petroleumController {

    @GetMapping
    public Result<String> test(){
        System.out.println("业务测试连接成功");
        return Result.success("业务测试连接成功");
    }
}
