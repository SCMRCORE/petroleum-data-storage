package com.petroleumserver.controller;

import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.*;
import com.petroleumpojo.vo.WanGongVO;
import com.petroleumserver.service.petroleumService;
import com.petroleumserver.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/petroleum")
@Slf4j
public class petroleumController {

    @Resource
    private petroleumService petroleumService;


    @Resource
    private MinioUtils minioUtils;

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
        log.info("执行addJS方法,传入数量:{}", jsDto.size());
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
        log.info("执行addJB方法,传入数量:{}", jbDto.size());
        petroleumService.addJB(jbDto);
        return Result.success();
    }

    /**
     * 新增FZ
     * 
     * @return
     */
    @PostMapping("/addfz") // 待实现
    public Result addfz(@RequestBody List<FuZaDTO> fzDto) {
        log.info("执行addFZ方法,传入数量:{}", fzDto.size());
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
        log.info("执行addZT方法,传入数量:{}", ztDto.size());
        petroleumService.addZT(ztDto);
        return Result.success();
    }

    /**
     * 搜索JingShen
     * 
     * @return
     */
    @PostMapping("/searchJS")
    public Result<PageResult> searchJS(@RequestBody JingShenSearchPageDTO jsSPDto) {
        log.info("执行searchJS方法:{}", jsSPDto);
        PageResult res = petroleumService.searchjs(jsSPDto);
        return Result.success(res);
    }

    /**
     * 搜索JiBen
     * 
     * @return
     */
    @PostMapping("/searchJB")
    public Result<PageResult> searchJB(@RequestBody JiBenSearchPageDTO jbSPDto) {
        log.info("执行searchJB方法:{}", jbSPDto);
        PageResult res = petroleumService.searchjb(jbSPDto);
        return Result.success(res);
    }

    /**
     * 搜索FuZa
     * 
     * @return
     */
    @PostMapping("/searchFZ")
    public Result<PageResult> searchFZ(@RequestBody FuZaSearchPageDTO fzSPDto) {
        log.info("执行searchFZ方法:{}", fzSPDto);
        PageResult res = petroleumService.searchfz(fzSPDto);
        return Result.success(res);
    }

    /**
     * 搜索ZuanTou
     * 
     * @return
     */
    @PostMapping("/searchZT")
    public Result<PageResult> searchZT(@RequestBody ZuanTouSearchPageDTO ztSPDto) {
        log.info("执行searchZT方法:{}", ztSPDto);
        PageResult res = petroleumService.searchzt(ztSPDto);
        return Result.success(res);
    }

    /**
     * 搜索WanGong
     * @return
     */
    @PostMapping("/searchWG")   //TODO 待实现搜索完工接口
    public Result<PageResult> searchWG(@RequestBody ZuanTouSearchPageDTO ztSPDto) {
//        log.info("执行searchZT方法:{}", ztSPDto);
//        PageResult res = petroleumService.searchzt(ztSPDto);
//        return Result.success(res);
        return Result.success();
    }


    /**
     * 软删除
     * @param params
     * @return
     */
    @PutMapping("/delete")//TODO 待实现删除
    public Result delete(@RequestBody Map<String, Integer> params) {
         log.info("执行delete方法删除:{}", params);
         petroleumService.updateStatus(params.get("num"), params.get("OnlyKey"));
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
        log.info("执行setJS方法更新:{}， 传入参数:{}", OnlyKey, jsDto);
        petroleumService.updateJS(OnlyKey, jsDto);
        return Result.success();
    }

    /**
     * 更新JB
     * @param OnlyKey
     * @param jbDto
     * @return
     */
    @PutMapping("/setJB")
    public Result set(Integer OnlyKey, @RequestBody JiBenDTO jbDto) {
        log.info("执行setJB方法更新:{}，传入参数:{}", OnlyKey, jbDto);
        petroleumService.updateJB(OnlyKey, jbDto);
        return Result.success();
    }

    /**
     * 更新FZ
     * @param OnlyKey
     * @param fzDto
     * @return
     */
    @PutMapping("/setFZ")
    public Result set(Integer OnlyKey, @RequestBody FuZaDTO fzDto) {
        log.info("执行setFZ方法更新:{}，传入参数:{}", OnlyKey, fzDto);
        petroleumService.updateFZ(OnlyKey, fzDto);
        return Result.success();
    }

    /**
     * 更新ZT
     * @param OnlyKey
     * @param ztDto
     * @return
     */
    @PutMapping("/setZT")
    public Result set(Integer OnlyKey, @RequestBody ZuanTouDTO ztDto) {
        log.info("执行setZT方法更新:{}，传入参数:{}", OnlyKey, ztDto);
        petroleumService.updateZT(OnlyKey, ztDto);
        return Result.success();
    }


    /**
     * 上传完工报告表(word)
     * @param word
     * @return
     */
    @PostMapping("/uploadWG")
    public Result addWG(@RequestParam("word") MultipartFile word, @RequestParam("wellName") String wellName)
            throws IOException {
        log.info("完工报告word: {}, 关联井名 {}", word, wellName);
        Long size = word.getSize();
        log.info("上传完工报告word:{}, 内存大小:{}", word, size);
        //如果小于20MB
        if(size < 20 * 1024 * 1024){
            WanGongDTO wanGongDTO = minioUtils.upload(word);
            wanGongDTO.setWellName(wellName);
            if(petroleumService.addWG(wanGongDTO)){
                WanGongVO vo = new WanGongVO();
                BeanUtils.copyProperties(vo, wanGongDTO);
                return Result.success(vo);
            }else {
                return Result.error("上传失败");
            }
        }
        else{
            return Result.error("文件大小超过20MB");
        }
    }

    /**
     * 查看完工报告表
     */

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

//    /**
//     * 批量新增
//     *
//     * @return
//     */
//    @PostMapping("/upload") // 表格需要处理成特定格式，我写在文档里了
//    public Result addByExcel(MultipartFile file, String company, Integer num) throws IOException {
//        log.info("执行upload方法");
//        petroleumService.addByList(file, company, num);
//        return Result.success();
//    }
