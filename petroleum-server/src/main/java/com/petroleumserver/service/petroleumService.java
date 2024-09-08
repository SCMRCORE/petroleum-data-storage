package com.petroleumserver.service;

import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface petroleumService {
    PageResult searchjs(JingShenSearchPageDTO jsSPDto);

    PageResult searchjb(JiBenSearchPageDTO jbSPDto);

    PageResult searchfz(FuZaSearchPageDTO fzSPDto);

    PageResult searchzt(ZuanTouSearchPageDTO ztSPDto);

    void addByList(MultipartFile file, String company, Integer num) throws IOException;

    void addJS(List<JingShenDTO> jsDto);

    void addJB(List<JiBenDTO> jbDto);

    void addFZ(List<FuZaDTO> fzDto);

    void addZT(List<ZuanTouDTO> ztDto);
}
