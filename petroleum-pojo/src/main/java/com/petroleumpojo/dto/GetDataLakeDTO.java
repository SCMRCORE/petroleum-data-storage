package com.petroleumpojo.dto;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

/**
 * 向数据湖发送请求的实体类
 */
@Data
public class GetDataLakeDTO {
    private int pageIndex;
    private int pageSize;
    private DataLakeDTO jsonObj;
}
