package com.petroleumserver.controller;

import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.DataLakeDTO;
import com.petroleumpojo.dto.DataLakeSearchPageDTO;
import com.petroleumpojo.entity.DataLake;
import com.petroleumpojo.vo.DataLakeVO;
import com.petroleumserver.service.dataLakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/dataLake")
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
        String appCode = dataLakeService.connect();
        log.info("接收到appcode: {}", appCode);
        return Result.success();
    }

    /**
     * 对数据进行查询,之后直接返回到前端查询内容
     * @return
     * @throws IOException
     */
    @PostMapping("/query")
    public Result<PageResult> query(@RequestBody DataLakeSearchPageDTO dto) throws IOException {
        PageResult query = dataLakeService.query(dto);
        return Result.success(query);
    }

}
