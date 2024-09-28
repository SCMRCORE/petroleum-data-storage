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
public class JiBen {
    private String wellName; // 井名
    private String company;//公司
    private String oilFieldName; // 油田名
    private String oilFieldCode; // 油田代码
    private String isCooperative; // 是否合作
    private String contractor; // 承包商
    private String primaryWellType; // 一级井别
    private String secondaryWellType; // 二级井别
    private String tertiaryWellType; // 三级井别
    private String wellType; // 井型
    private String waterDepth1; // 水深
    private String designDepth; // 设计深度
    private String designVerticalDepth; // 设计垂深
    private String  designDate; // 设计时间
    private String structureName; // 构造名称
    private String uniqueWellId; // 井唯一识别号
    private String chineseWellName; // 中文井名
    private String operator; // 作业者
    private String hydrocarbonType; // 油气类型
    private String riskLevel; // 风险等级
    private String riskType; // 风险类型
    private String riskGrade; // 风险级别
    private String sulfurGrade; // 含硫级别
    private String containsCO2; // 是否含CO2
    private String zeroDepth; // 深度零点
    private String initialKellyElevation; // 初始补心海拔
    private String groundElevation; // 地面海拔
    private String currentKellyElevation; // 当前补心海拔
    private String casingHeadElevation; // 套管头海拔
    private String waterDepth2; // 水深_重复（油管法兰海拔）
    private String mudLineElevation; // 泥线海拔
    private String artificialBottomDepth; // 人工底部深度
    private String drillingDays; // 钻井天数
    private String positioningMethod; // 定位方法
    private String location; // 位置
    private String geodeticSystem; // 大地测量系统
    private String longitude; // 经度
    private String latitude; // 纬度
    private String xcoordinate; // X坐标
    private String ycoordinate; // Y坐标
    private String slotNumber; // 槽口号
    private String country; // 国家
    private String region; // 区域
    private String province; // 州省
    private String county; // 县
    private String scrapedWell; // 工程报废井
    private String officePhone; // 现场办公室电话
    private String remark; // 备注
    private Integer status;
    private Integer OnlyKey;
    private Integer num;
}
