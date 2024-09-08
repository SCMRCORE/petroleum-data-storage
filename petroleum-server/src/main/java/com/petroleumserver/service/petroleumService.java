package com.petroleumserver.service;

import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface petroleumService {
    PageResult searchjs(JingShenSearchPageDTO jsSPDto);

    PageResult searchjb(JiBenSearchPageDTO jbSPDto);

    PageResult searchfz(FuZaSearchPageDTO fzSPDto);

    PageResult searchzt(ZuanTouSearchPageDTO ztSPDto);

    void addByList(MultipartFile file, String company, Integer num) throws IOException;

    void addJS(JingShenDTO jsDto, String company);
}
