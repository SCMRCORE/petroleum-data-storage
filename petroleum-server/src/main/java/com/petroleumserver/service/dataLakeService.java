package com.petroleumserver.service;

import com.aliyuncs.kms.model.v20160120.DeleteSecretRequest;
import com.petroleumcommom.result.PageResult;
import com.petroleumpojo.dto.DataLakeSearchPageDTO;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public interface dataLakeService {

    String connect() throws IOException, InterruptedException;

    /**
     * 直接拿到json格式数据返回前端
     * @param json
     * @return
     */
    String query(String json) throws IOException, InterruptedException;
}
