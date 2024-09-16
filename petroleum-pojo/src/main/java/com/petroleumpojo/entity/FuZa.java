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
    private String wellName; // 井名
    private String company; // 公司
    private String primaryWellType; // 一级井别
    private String wellType; // 井型
//    private LocalDateTime startTime; // 开始时间
//    private LocalDateTime endTime; // 结束时间
    private String startTime; // 开始时间
    private String endTime; // 结束时间
    private String durationHours; // 持续时间（hr）
    private String firstLevelCode; // 一级代码
    private String secondLevelCode; // 二级代码
    private String thirdLevelCode; // 三级代码
    private String fourthLevelCode; // 四级代码
    private String complexityType; // 复杂情况类型
    private String wellSection; // 井眼段
    private String startDepth; // 开始深度（m）
    private String endDepth; // 结束深度（m）
    private String operationDescription; // 作业描述
    private Integer status;
    private Integer OnlyKey;
}
