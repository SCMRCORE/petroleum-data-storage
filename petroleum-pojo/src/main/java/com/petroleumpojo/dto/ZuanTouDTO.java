package com.petroleumpojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZuanTouDTO {
    private String wellName; // 井名
    private String company;
    private String primaryWellType; // 一级井别
    private String wellType; // 井型
    private String drillBitNumber; // 钻头编号
    private String drillBitType; // 钻头类型
    private String sizeIn; // 尺寸（in）
    private String manufacturer; // 厂家
    private String model; // 型号
    private String serialNumber; // 序列号
    private String nozzleSize; // 喷嘴（1/32\"）
    private String drillBitTFAn2; // 钻头（TFAn2）
    private String entryDepth; // 入井深度（m）
    private String exitDepth; // 出井深度（m）
    private LocalDateTime entryTime; // 入井时间
    private LocalDateTime exitTime; // 出井时间
    private String advanceDistance; // 进尺（m）
    private String pureDrillingTime; // 纯钻时间（h）
    private String rop; // ROP（m/h）
    private String maxDrillingPressure; // 最大钻压（tonne）
    private String minDrillingPressure; // 最小钻压（tonne）
    private String maxRotationSpeed; // 最大转速（RPM）
    private String minRotationSpeed; // 最小转速（RPM）
    private String iadcWearEvaluation; // 钻头IADC磨损评价
    private String encounteredFormation; // 钻遇地层
}
