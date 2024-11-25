package com.petroleumpojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuZa {
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
    private Integer status;
    private Integer OnlyKey;
    private Integer num;
}
