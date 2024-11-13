package com.petroleumserver.service;

import java.io.IOException;

public interface DataLakeService {

    String connect() throws IOException, InterruptedException;

    /**
     * 直接拿到json格式数据返回前端
     * @param json
     * @return
     */
    String query(String json) throws IOException, InterruptedException;
}
