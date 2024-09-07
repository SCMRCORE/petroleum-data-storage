package com.petroleumserver.service;

import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.JingShenSearchPageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface petroleumService {
    void addByList(MultipartFile file, String company, Integer num) throws IOException;

    PageResult search(JingShenSearchPageDTO jsSPDto);
}
