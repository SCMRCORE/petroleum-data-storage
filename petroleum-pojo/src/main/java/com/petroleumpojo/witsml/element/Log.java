package com.petroleumpojo.witsml.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import lombok.Data;

@Data
@XmlRootElement(name = "log")
@XmlAccessorType(XmlAccessType.FIELD)
public class Log {

    // 井的唯一标识符
    @XmlElement(name = "uidWell")
    private String uidWell;

    // 井眼的唯一标识符
    @XmlElement(name = "uidWellbore")
    private String uidWellbore;

    // 日志的唯一标识符
    @XmlElement(name = "uid")
    private String uid;

    // 井的名称
    @XmlElement(name = "nameWell")
    private String nameWell;

    // 井眼的名称
    @XmlElement(name = "nameWellbore")
    private String nameWellbore;

    // 日志的名称
    @XmlElement(name = "name")
    private String name;

    // 对象是否正在增长（某些特定情境使用）
    @XmlElement(name = "objectGrowing")
    private String objectGrowing;

    // 提供服务的公司名称
    @XmlElement(name = "serviceCompany")
    private String serviceCompany;

    // 日志的运行编号
    @XmlElement(name = "runNumber")
    private String runNumber;

    // 索引类型（例如：日期时间或测量深度等）
    @XmlElement(name = "indexType")
    private String indexType;

    // 索引的开始时间
    @XmlElement(name = "startDateTimeIndex")
    private String startDateTimeIndex;

    // 索引的结束时间
    @XmlElement(name = "endDateTimeIndex")
    private String endDateTimeIndex;

    // 指定的索引曲线列索引
    @XmlElement(name = "indexCurve")
    private String indexCurve;

    // 空值表示（如果某些值无效或缺失时使用）
    @XmlElement(name = "nullValue")
    private String nullValue;
//
//    // 描述日志曲线的元数据（如单位、值的类型等）
//    @XmlElement(name = "logCurveInfo")
//    private LogCurveInfo logCurveInfo;

    // 包含日志数据的具体内容
    @XmlElement(name = "logData")
    private LogData logData;
}
