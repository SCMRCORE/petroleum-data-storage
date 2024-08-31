package com.petroleumserver.controller;

import com.petroleumcommom.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class userController {

    @GetMapping
    public Result<String> test(){
        System.out.println("用户测试连接成功");
        return Result.success("用户测试连接成功");
    }
}
