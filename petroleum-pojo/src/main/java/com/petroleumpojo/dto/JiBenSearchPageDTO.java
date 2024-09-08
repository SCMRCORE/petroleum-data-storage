package com.petroleumpojo.dto;

import lombok.Data;

@Data
public class JiBenSearchPageDTO {
    private Integer pageIndex;
    private Integer pageSize;
    private String wellName;
    private String oilFieldName;
    private String contractor;
}
