//package com.petroleumserver.utils;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.petroleumcommom.result.PageResult;
////import com.petroleumpojo.dto.DataLakeDTO;
//import com.petroleumpojo.dto.DataLakeDTO;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 使用json工具将请求到的DataLake数据
// * 进行作为分页结果进行返回
// */
//@Component
//public class JsonParseUtil  {
//    public PageResult parseResponse(String jsonResponse) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Parse the entire JSON structure into a Map
//        Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, Map.class);
//
//        // Extract total and data from the response
//        long total = (long) responseMap.get("total");
//        List<Map<String, Object>> data = (List<Map<String, Object>>) responseMap.get("data");
//
//        // Convert data into a list of DataLakeDTO objects
//        List<DataLakeDTO> records = objectMapper.convertValue(data, new TypeReference<List<DataLakeDTO>>() {});
//
//        // Create PageResult object and set the values
//        PageResult pageResult = new PageResult();
//        pageResult.setTotal(total);
////        pageResult.setRecords(records);
//
//        return pageResult;
//    }
//
//}
