package com.petroleumserver.controller;




import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.JingShenSearchPageDTO;
import com.petroleumserver.service.petroleumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/petroleum")
@Slf4j
public class petroleumController {

    @Resource
    private petroleumService petroleumService;

    @GetMapping
    public Result<String> test(){
        System.out.println("业务测试连接成功");
        return Result.success("业务测试连接成功");
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("/add")//待实现
    public Result add(){
        return Result.success();
    }

    /**
     * 批量新增
     * @return
     */
    @PostMapping("/upload")//表格需要处理成特定格式，我写在文档里了
    public Result addByExcel(MultipartFile file, String company, Integer num) throws IOException {
         petroleumService.addByList(file, company, num);
         return Result.success();
    }


    /**
     * 搜索JingShen
     * @return
     */
    @GetMapping("/searchJS")//剩余三张表搜索待实现
    public Result<PageResult> search(JingShenSearchPageDTO jsSPDto){
        PageResult res = petroleumService.search(jsSPDto);
        return Result.success(res);
    }

    @DeleteMapping("/delete")
    public Result delete(String wellName, Integer num){
        //num是表格种类,1是JinShen,2是JiBen,3是FuZa,4是钻头
        //status的状态，1是存在，2是删除
        //至于wellName是依照字段删除的其中一个举例
        return Result.success();
    }


    //修改估计得做个接口，对应四个表
    @PutMapping("/set")
    public Result set(String wellName, Integer num){
        //num是表格种类,1是JinShen,2是JiBen,3是FuZa,4是钻头
        //至于wellName是依照字段删除的其中一个举例
        return Result.success();
    }

}
