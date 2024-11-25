package com.petroleumserver.controller;

import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.*;
import com.petroleumserver.service.PetroleumService;
import com.petroleumserver.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/petroleum")
@Slf4j
public class petroleumController {

    @Resource
    private PetroleumService petroleumService;

    @GetMapping
    public Result<String> test() {
        System.out.println("业务测试连接成功");
        return Result.success("业务测试连接成功");
    }

     
    @PostMapping("/addjs") 
    public Result addjs(@RequestBody List<JingShenDTO> jsDto) {
        log.info("执行addJS方法,传入数量:{}", jsDto.size());
        petroleumService.addJS(jsDto);
        return Result.success();
    }

     
    @PostMapping("/addjb") 
    public Result addjb(@RequestBody List<JiBenDTO> jbDto) {
        log.info("执行addJB方法,传入数量:{}", jbDto.size());
        petroleumService.addJB(jbDto);
        return Result.success();
    }

     
    @PostMapping("/addfz") 
    public Result addfz(@RequestBody List<FuZaDTO> fzDto) {
        log.info("执行addFZ方法,传入数量:{}", fzDto.size());
        petroleumService.addFZ(fzDto);
        return Result.success();
    }

     
    @PostMapping("/addzt") 
    public Result addzt(@RequestBody List<ZuanTouDTO> ztDto) {
        log.info("执行addZT方法,传入数量:{}", ztDto.size());
        petroleumService.addZT(ztDto);
        return Result.success();
    }

     
    @PostMapping("/searchJS")
    public Result<PageResult> searchJS(@RequestBody JingShenSearchPageDTO jsSPDto) {
        log.info("执行searchJS方法:{}", jsSPDto);
        PageResult res = petroleumService.searchjs(jsSPDto);
        return Result.success(res);
    }

     
    @PostMapping("/searchJB")
    public Result<PageResult> searchJB(@RequestBody JiBenSearchPageDTO jbSPDto) {
        log.info("执行searchJB方法:{}", jbSPDto);
        PageResult res = petroleumService.searchjb(jbSPDto);
        return Result.success(res);
    }

     
    @PostMapping("/searchFZ")
    public Result<PageResult> searchFZ(@RequestBody FuZaSearchPageDTO fzSPDto) {
        log.info("执行searchFZ方法:{}", fzSPDto);
        PageResult res = petroleumService.searchfz(fzSPDto);
        return Result.success(res);
    }

     
    @PostMapping("/searchZT")
    public Result<PageResult> searchZT(@RequestBody ZuanTouSearchPageDTO ztSPDto) {
        log.info("执行searchZT方法:{}", ztSPDto);
        PageResult res = petroleumService.searchzt(ztSPDto);
        return Result.success(res);
    }

     
    @PostMapping("/searchWG")   
    public Result<PageResult> searchWG(@RequestBody ZuanTouSearchPageDTO ztSPDto) {   
        return Result.success();
    }


     
    @PutMapping("/delete" 
    public Result delete(@RequestBody Map<String, Integer> params) {
         log.info("执行delete方法删除:{}", params);
         petroleumService.updateStatus(params.get("num"), params.get("OnlyKey"));
        return Result.success();
    }


     
    @PutMapping("/setJS")
    public Result set(Integer OnlyKey, @RequestBody JingShenDTO jsDto) {
        log.info("执行setJS方法更新:{}， 传入参数:{}", OnlyKey, jsDto);
        petroleumService.updateJS(OnlyKey, jsDto);
        return Result.success();
    }

     
    @PutMapping("/setJB")
    public Result set(Integer OnlyKey, @RequestBody JiBenDTO jbDto) {
        log.info("执行setJB方法更新:{}，传入参数:{}", OnlyKey, jbDto);
        petroleumService.updateJB(OnlyKey, jbDto);
        return Result.success();
    }

     
    @PutMapping("/setFZ")
    public Result set(Integer OnlyKey, @RequestBody FuZaDTO fzDto) {
        log.info("执行setFZ方法更新:{}，传入参数:{}", OnlyKey, fzDto);
        petroleumService.updateFZ(OnlyKey, fzDto);
        return Result.success();
    }

     
    @PutMapping("/setZT")
    public Result set(Integer OnlyKey, @RequestBody ZuanTouDTO ztDto) {
        log.info("执行setZT方法更新:{}，传入参数:{}", OnlyKey, ztDto);
        petroleumService.updateZT(OnlyKey, ztDto);
        return Result.success();
    }



}
                                               
           
