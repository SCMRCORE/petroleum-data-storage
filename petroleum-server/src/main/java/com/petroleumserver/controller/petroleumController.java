package com.petroleumserver.controller;
import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.*;
import com.petroleumserver.service.petroleumService;
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

    @GetMapping
    public Result<String> test(){
        System.out.println("业务测试连接成功");
        return Result.success("业务测试连接成功");
    }

    /**
     * 新增JS
     * @return
     */
    @PostMapping("/addjs")//待实现
    public Result addjs(@RequestBody List<JingShenDTO> jsDto){
        petroleumService.addJS(jsDto);
        return Result.success();
    }
    /**
     * 新增JB
     * @return
     */
    @PostMapping("/addjb")//待实现
    public Result addjb(@RequestBody List<JiBenDTO> jbDto){
        petroleumService.addJB(jbDto);
        return Result.success();
    }
    /**
     * 新增FZ
     * @return
     */
    @PostMapping("/addfz")//待实现
    public Result addfz(@RequestBody List<FuZaDTO> fzDto){
        petroleumService.addFZ(fzDto);
        return Result.success();
    }
    /**
     * 新增ZT
     * @return
     */
    @PostMapping("/addzt")//待实现
    public Result addzt(@RequestBody List<ZuanTouDTO> ztDto){
        petroleumService.addZT(ztDto);
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
    @GetMapping("/searchJS")
    public Result<PageResult> searchJS(JingShenSearchPageDTO jsSPDto){
        PageResult res = petroleumService.searchjs(jsSPDto);
        return Result.success(res);
    }
    /**
     * 搜索JiBen
     * @return
     */
    @GetMapping("/searchJB")
    public Result<PageResult> searchJB(JiBenSearchPageDTO jbSPDto){
        PageResult res = petroleumService.searchjb(jbSPDto);
        return Result.success(res);
    }
    /**
     * 搜索FuZa
     * @return
     */
    @GetMapping("/searchFZ")
    public Result<PageResult> searchFZ(FuZaSearchPageDTO fzSPDto){
        PageResult res = petroleumService.searchfz(fzSPDto);
        return Result.success(res);
    }
    /**
     * 搜索ZuanTou
     * @return
     */
    @GetMapping("/searchZT")
    public Result<PageResult> searchZT(ZuanTouSearchPageDTO ztSPDto){
        PageResult res = petroleumService.searchzt(ztSPDto);
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
