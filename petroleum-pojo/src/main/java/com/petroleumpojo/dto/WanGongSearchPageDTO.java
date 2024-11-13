package com.petroleumpojo.dto;

import lombok.Data;

@Data
public class WanGongSearchPageDTO {
    private String WellName;
    private String fileName;
    private String uploadTime;
    private Integer pageIndex;
    private Integer pageSize;
}
