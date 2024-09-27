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
    private String wellSection;
    private String holeDiameter;
    private String startTime;
    private String endTime;
    private String startDepth;
    private String startVerticalDepth;
    private String endDepth;
    private String endVerticalDepth;
    private String casingDiameter;
    private String steelGrade;
    private String casingSetDepth;
    private String casingLength;
    private String cementReturnHeight;
    private String upperCasingDiameter;
    private String upperCasingSetDepth;
    private String drillingFluidSystem;
    private String drillingFluidDensity;
    private String plasticViscosity;
    private String yieldValue;
    private String formationPressure;
    private Integer OnlyKey;
}
