package com.petroleumserver.service;

import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PetroleumService {
    PageResult searchjs(JingShenSearchPageDTO jsSPDto);
    PageResult searchjb(JiBenSearchPageDTO jbSPDto);
    PageResult searchfz(FuZaSearchPageDTO fzSPDto);
    PageResult searchzt(ZuanTouSearchPageDTO ztSPDto);
    PageResult searchFile(WanGongSearchPageDTO dto);


    void addByList(MultipartFile file, String company, Integer num) throws IOException;


    void updateStatus(Integer num, Integer onlyKey);


    void addJS(List<JingShenDTO> jsDto);
    void addJB(List<JiBenDTO> jbDto);
    void addFZ(List<FuZaDTO> fzDto);
    void addZT(List<ZuanTouDTO> ztDto);

//    void updateStatusJS(JingShenDTO jsDto);
//    void updateStatusJB(JiBenDTO jbDto);
//    void updateStatusFZ(FuZaDTO fzDto);
//    void updateStatusZT(ZuanTouDTO ztDto);


    void updateJS(Integer onlyKey, JingShenDTO jsDto);
    void updateJB(Integer onlyKey, JiBenDTO jbDto);
    void updateFZ(Integer onlyKey, FuZaDTO fzDto);

    void updateZT(Integer onlyKey, ZuanTouDTO ztDto);

    boolean addWG(WanGongDTO dto);

    void deleteFile(WanGongDTO dto);
}
