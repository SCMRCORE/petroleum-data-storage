package com.petroleumpojo.dto;

import lombok.Data;

import java.lang.annotation.Target;
import java.time.LocalDateTime;

@Data
public class JingShenDTO {
    private String wellName;
    private String company;
    private String primaryWellType;
    private String wellType;
    private String WellSection;
    private String HoleDiameter;
//    private LocalDateTime StartTime;
//    private LocalDateTime EndTime;
    private String StartTime;
    private String EndTime;
    private String StartDepth;
    private String StartVerticalDepth;
    private String EndDepth;
    private String EndVerticalDepth;
    private String CasingDiameter;
    private String SteelGrade;
    private String CasingSetDepth;
    private String CasingLength;
    private String CementReturnHeight;
    private String UpperCasingDiameter;
    private String UpperCasingSetDepth;
    private String DrillingFluidSystem;
    private String DrillingFluidDensity;
    private String PlasticViscosity;
    private String YieldValue;
    private String FormationPressure;
    private Integer OnlyKey;
}
