package com.petroleumserver.service;

import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface dataLakeService {

    void connect() throws IOException;
}
