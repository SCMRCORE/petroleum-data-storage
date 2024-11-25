package com.petroleumpojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuZaDTO {
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
    private Integer OnlyKey;
}
