package com.petroleumpojo.entity;

import cn.hutool.core.annotation.Alias;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JingShen {
    private String WellName;
    private String company;
    private String PrimaryWellType;
    private String WellType;
    private String WellSection;
    private String HoleDiameter;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
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
    private Integer status;
}
