package com.petroleumserver.service;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DataLakeService {

    String connect() throws IOException, InterruptedException;

    /**
     * 直接拿到json格式数据返回前端
     *
     * @param json
     * @return
     */
    ResponseEntity<String> query(String json, Integer index) throws IOException, InterruptedException;
}
