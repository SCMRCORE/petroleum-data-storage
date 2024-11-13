package com.petroleumserver.controller;


import com.petroleumcommom.result.PageResult;
import com.petroleumcommom.result.Result;
import com.petroleumpojo.dto.WanGongDTO;
import com.petroleumpojo.dto.WanGongSearchPageDTO;
import com.petroleumpojo.vo.WanGongVO;
import com.petroleumserver.service.PetroleumService;
import com.petroleumserver.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("file")
@Slf4j
public class FileController {

    @Resource
    MinioUtils minioUtils;

    @Resource
    private PetroleumService petroleumService ;

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
     * 搜索完工报告
     */
    @PostMapping("/search")
    public Result<PageResult> searchFile(@RequestBody WanGongSearchPageDTO dto) {
        log.info("开始执行搜索完工报告，{}", dto);
        PageResult pageResult = petroleumService.searchFile(dto);
        return Result.success(pageResult);
    }

    /**
     * 删除完工报告
     */



}
