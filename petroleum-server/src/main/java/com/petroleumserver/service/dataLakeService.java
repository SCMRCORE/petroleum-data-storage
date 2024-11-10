package com.petroleumserver.service;

import com.aliyuncs.kms.model.v20160120.DeleteSecretRequest;
import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.DataLakeSearchPageDTO;

import java.io.IOException;

public interface dataLakeService {

    String connect() throws IOException;

    PageResult query(DataLakeSearchPageDTO dto);
}
