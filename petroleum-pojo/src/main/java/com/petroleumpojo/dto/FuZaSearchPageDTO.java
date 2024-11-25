package com.petroleumpojo.dto;

import lombok.Data;

@Data
public class FuZaSearchPageDTO {
    private Integer pageIndex;
    private Integer pageSize;
    private String wellName; 
    private String company;
    private String primaryWellType; 
    private String wellType; 
     
    private String startTime; 
    private String endTime; 
    private String durationHours; 
    private String firstLevelCode; 
    private String secondLevelCode; 
    private String thirdLevelCode; 
    private String fourthLevelCode; 
    private String complexityType; 
    private String wellSection; 
    private String startDepth; 
    private String endDepth; 
    private String operationDescription; 
}
