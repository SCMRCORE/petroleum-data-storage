package com.petroleumserver.controller;

import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.*;
import com.petroleumpojo.entity.JiBen;
import com.petroleumpojo.entity.ZuanTou;
import com.petroleumserver.service.petroleumService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/petroleum")
@Slf4j
public class petroleumController {

    @Resource
    private petroleumService petroleumService;

    @CrossOrigin
    @GetMapping
    public Result<String> test() {
        System.out.println("业务测试连接成功");
        return Result.success("业务测试连接成功");
    }

    /**
     * 新增JS
     * 
     * @return
     */
    @PostMapping("/addjs") // 待实现
    public Result addjs(@RequestBody List<JingShenDTO> jsDto) {
        petroleumService.addJS(jsDto);
        return Result.success();
    }

    /**
     * 新增JB
     * 
     * @return
     */
    @PostMapping("/addjb") // 待实现
    public Result addjb(@RequestBody List<JiBenDTO> jbDto) {
        petroleumService.addJB(jbDto);
        return Result.success();
    }

    /**
     * 新增FZ
     * 
     * @return
     */
    @CrossOrigin
    @PostMapping("/addfz") // 待实现
    public Result addfz(@RequestBody List<FuZaDTO> fzDto) {
        petroleumService.addFZ(fzDto);
        return Result.success();
    }

    /**
     * 新增ZT
     * 
     * @return
     */
    @PostMapping("/addzt") // 待实现
    public Result addzt(@RequestBody List<ZuanTouDTO> ztDto) {
        petroleumService.addZT(ztDto);
        return Result.success();
    }

    /**
     * 批量新增
     * 
     * @return
     */
    @PostMapping("/upload") // 表格需要处理成特定格式，我写在文档里了
    public Result addByExcel(MultipartFile file, String company, Integer num) throws IOException {
        petroleumService.addByList(file, company, num);
        return Result.success();
    }

    /**
     * 搜索JingShen
     * 
     * @return
     */
    @GetMapping("/searchJS")
    public Result<PageResult> searchJS(JingShenSearchPageDTO jsSPDto) {
        System.out.println("todo: 这没传参数要兜底的，返回全部数据（或者返回第一页的数据）");
        PageResult res = petroleumService.searchjs(jsSPDto);
        System.out.println(res);
        return Result.success(res);
    }

    /**
     * 搜索JiBen
     * 
     * @return
     */
    @GetMapping("/searchJB")
    public Result<PageResult> searchJB(JiBenSearchPageDTO jbSPDto) {
        PageResult res = petroleumService.searchjb(jbSPDto);
        return Result.success(res);
    }

    /**
     * 搜索FuZa
     * 
     * @return
     */
    @GetMapping("/searchFZ")
    public Result<PageResult> searchFZ(FuZaSearchPageDTO fzSPDto) {
        PageResult res = petroleumService.searchfz(fzSPDto);
        return Result.success(res);
    }

    /**
     * 搜索ZuanTou
     * 
     * @return
     */
    @GetMapping("/searchZT")
    public Result<PageResult> searchZT(ZuanTouSearchPageDTO ztSPDto) {
        PageResult res = petroleumService.searchzt(ztSPDto);
        return Result.success(res);
    }

    // /**
    // * 软删除JS
    // * @param jsDto
    // * @return
    // */
    // @PutMapping("/deleteJS")
    // public Result deleteJS(@RequestBody JingShenDTO jsDto){
    //// System.out.println(jsDto);
    // petroleumService.updateStatusJS(jsDto);
    // return Result.success();
    // }
    //
    // /**
    // * 软删除JB
    // * @param jbDto
    // * @return
    // */
    // @PutMapping("/deleteJB")
    // public Result deleteJB(@RequestBody JiBenDTO jbDto){
    //// System.out.println(jbDto);
    // petroleumService.updateStatusJB(jbDto);
    // return Result.success();
    // }
    //
    // /**
    // * 软删除FZ
    // * @param fzDto
    // * @return
    // */
    // @PutMapping("/deleteFZ")
    // public Result deleteFZ(@RequestBody FuZaDTO fzDto){
    //// System.out.println(fzDto);
    // petroleumService.updateStatusFZ(fzDto);
    // return Result.success();
    // }
    //
    // /**
    // * 软删除ZT
    // * @param ztDto
    // * @return
    // */
    // @PutMapping("/deleteZT")
    // public Result deleteZT(@RequestBody ZuanTouDTO ztDto){
    //// System.out.println(ztDto);
    // petroleumService.updateStatusZT(ztDto);
    // return Result.success();
    // }

    /**
     * 软删除最新
     * 
     * @param OnlyKey
     * @param num
     * @return
     */
    @PutMapping("/delete")
    public Result delete(Integer OnlyKey, Integer num) {
        // System.out.println(OnlyKey);
        petroleumService.updateStatus(num, OnlyKey);
        return Result.success();
    }

    /**
     * 更新JS
     * 
     * @param OnlyKey
     * @param jsDto
     * @return
     */
    @PutMapping("/setJS")
    public Result set(Integer OnlyKey, @RequestBody JingShenDTO jsDto) {
        petroleumService.updateJS(OnlyKey, jsDto);
        return Result.success();
    }

    @PutMapping("/setJB")
    public Result set(Integer OnlyKey, @RequestBody JiBenDTO jbDto) {
        // petroleumService.updateJB(OnlyKey, jbDto);
        return Result.success();
    }

    @PutMapping("/setFZ")
    public Result set(Integer OnlyKey, @RequestBody FuZaDTO fzDto) {
        // petroleumService.updateFZ(OnlyKey, fzDto);
        return Result.success();
    }

    @PutMapping("/setZT")
    public Result set(Integer OnlyKey, @RequestBody ZuanTouDTO ztDto) {
        // petroleumService.updateZT(OnlyKey, ztDto);
        return Result.success();
    }

}
