package com.petroleumpojo.dto;

import lombok.Data;

@Data
public class ZuanTouSearchPageDTO {
    private Integer pageIndex;
    private Integer pageSize;
    private String wellName;
    private String primaryWellType;
    private String wellType;
}