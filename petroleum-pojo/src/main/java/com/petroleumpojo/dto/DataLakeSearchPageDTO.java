package com.petroleumpojo.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class DataLakeSearchPageDTO {
    private Integer pageIndex;
    private Integer pageSize;
    private DataLakeDTO jsonObj;
}
