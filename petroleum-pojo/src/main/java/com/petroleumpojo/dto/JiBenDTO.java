package com.petroleumpojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class JiBenDTO {
    private String wellName; 
    private String company;
    private String oilFieldName; 
    private String oilFieldCode; 
    private String isCooperative; 
    private String contractor; 
    private String primaryWellType; 
    private String secondaryWellType; 
    private String tertiaryWellType; 
    private String wellType; 
    private String waterDepth1; 
    private String designDepth; 
    private String designVerticalDepth; 
    private String  designDate; 
    private String structureName; 
    private String uniqueWellId; 
    private String chineseWellName; 
    private String operator; 
    private String hydrocarbonType; 
    private String riskLevel; 
    private String riskType; 
    private String riskGrade; 
    private String sulfurGrade; 
    private String containsCO2; 
    private String zeroDepth; 
    private String initialKellyElevation; 
    private String groundElevation; 
    private String currentKellyElevation; 
    private String casingHeadElevation; 
    private String waterDepth2; 
    private String mudLineElevation; 
    private String artificialBottomDepth; 
    private String drillingDays; 
    private String positioningMethod; 
    private String location; 
    private String geodeticSystem; 
    private String longitude; 
    private String latitude; 
    private String xcoordinate; 
    private String ycoordinate; 
    private String slotNumber; 
    private String country; 
    private String region; 
    private String province; 
    private String county; 
    private String scrapedWell; 
    private String officePhone; 
    private String remark; 
    private Integer OnlyKey;
}
