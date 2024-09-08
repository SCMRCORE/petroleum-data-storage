package com.petroleumserver.service;

import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.FuZaSearchPageDTO;
import com.petroleumpojo.dto.JiBenSearchPageDTO;
import com.petroleumpojo.dto.JingShenSearchPageDTO;
import com.petroleumpojo.dto.ZuanTouSearchPageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface petroleumService {
    void addByList(MultipartFile file, String company, Integer num) throws IOException;

    PageResult searchjs(JingShenSearchPageDTO jsSPDto);

    PageResult searchjb(JiBenSearchPageDTO jbSPDto);

    PageResult searchfz(FuZaSearchPageDTO fzSPDto);

    PageResult searchzt(ZuanTouSearchPageDTO ztSPDto);
}
