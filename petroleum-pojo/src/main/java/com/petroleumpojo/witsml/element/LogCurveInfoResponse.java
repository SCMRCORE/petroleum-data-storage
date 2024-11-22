package com.petroleumpojo.witsml.element;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data  // Lombok 注解，用于自动生成 getters, setters, toString, equals, hashCode 等
@XmlRootElement(name = "logCurveInfo")  // 定义 XML 根元素
@XmlAccessorType(XmlAccessType.FIELD)  // 使用字段访问模式
public class LogCurveInfoResponse {

    @XmlElement(name = "uid")  // 曲线的唯一标识符
    // 表示该曲线的唯一标识符，用于区分不同的测量曲线。
    private String uid;

    @XmlElement(name = "mnemonic")  // 曲线的简短标识符或标识符
    // 表示曲线的简短标识符，通常用于代表测量类型或物理量。
    private String mnemonic;

    @XmlElement(name = "classWitsml")  // 曲线的物理量类型
    // 表示曲线所描述的物理量的类型，如“测量深度”或“温度”。
    private String classWitsml;

    @XmlElement(name = "unit")  // 曲线数据的单位
    // 表示曲线数据的单位，例如“m”表示米（深度单位），“°C”表示摄氏度（温度单位）。
    private String unit;

    @XmlElement(name = "mnemAlias")  // 曲线标识符的别名
    private String mnemAlias;

    @XmlElement(name = "nullValue")  // 无效值的占位符
    // 表示无效值或缺失值，通常使用占位符（如 `-9999`）来标记数据点无效或缺失。
    private String nullValue;

    @XmlElement(name = "minDateTimeIndex")  // 数据的最早时间戳
    // 表示数据的起始时间，用于标识该曲线数据开始记录的时间。
    private String minDateTimeIndex;

    @XmlElement(name = "maxDateTimeIndex")  // 数据的最晚时间戳
    // 表示数据的结束时间，用于标识该曲线数据结束记录的时间。
    private String maxDateTimeIndex;

    @XmlElement(name = "columnIndex")  // 曲线在数据表中的列索引
    // 表示曲线在数据表中的列位置，用于确定该曲线的具体位置。
    private int columnIndex;

    @XmlElement(name = "traceState")  // 数据的处理状态
    // 表示数据的处理状态，常见的值包括“raw”（原始数据）和“processed”（已处理数据）。
    private String traceState;

    @XmlElement(name = "typeLogData")  // 曲线数据的类型
    // 表示曲线数据的类型，如 `double`（浮动数值）、`date time`（时间戳）。
    private String typeLogData;

    @XmlElement(name = "sensorOffset")  // 传感器的偏移量
    // 描述传感器的偏移量，通常用于校正测量数据。它表示传感器相对于基准位置的偏移值。
    private SensorOffset sensorOffset;

    // 内部类表示 sensorOffset，存储传感器的单位和偏移量
    @Data  // Lombok 注解，自动生成 getters 和 setters
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SensorOffset {

        @XmlElement(name = "uom")  // 偏移量的单位
        // 表示偏移量的单位，例如 `ft`（英尺）或 `m`（米）。
        private String uom;

        @XmlElement(name = "value")  // 偏移量的值
        // 表示传感器的偏移量值，通常为 `0` 表示没有偏移。
        private int value;
    }
}