package com.petroleumpojo.dto;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

 
@Data
public class GetDataLakeDTO {
    private int pageIndex;
    private int pageSize;
    private DataLakeDTO jsonObj;
}
