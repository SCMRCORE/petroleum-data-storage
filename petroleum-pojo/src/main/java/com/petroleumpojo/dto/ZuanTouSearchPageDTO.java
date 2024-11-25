package com.petroleumpojo.dto;

import lombok.Data;

@Data
public class ZuanTouSearchPageDTO {
    private Integer pageIndex;
    private Integer pageSize;
    private String wellName; 
    private String company;
    private String primaryWellType; 
    private String wellType; 
    private String drillBitNumber; 
    private String drillBitType; 
    private String sizeIn; 
    private String manufacturer; 
    private String model; 
    private String serialNumber; 
    private String nozzleSize; 
    private String drillBitTFAn2; 
    private String entryDepth; 
    private String exitDepth; 
    private String entryTime; 
    private String exitTime; 
    private String advanceDistance; 
    private String pureDrillingTime; 
    private String rop; 
    private String maxDrillingPressure; 
    private String minDrillingPressure; 
    private String maxRotationSpeed; 
    private String minRotationSpeed; 
    private String iadcWearEvaluation; 
    private String encounteredFormation; 
}
