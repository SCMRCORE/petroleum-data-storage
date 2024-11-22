package com.petroleumpojo.witsml.element;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@Data
@XmlRootElement(name = "logCurveInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogCurveInfoRequest {

    // 曲线的标识符
    @XmlElement(name = "mnemonic")
    private String mnemonic;

    // 曲线的类型
    @XmlElement(name = "classWitsml")
    private String classWitsml;

    // 曲线的单位
    @XmlElement(name = "unit")
    private String unit;

    // 曲线的别名
    @XmlElement(name = "mnemAlias")
    private String mnemAlias;

    // 曲线的空值表示
    @XmlElement(name = "nullValue")
    private String nullValue;

    // 曲线的最小时间索引
    @XmlElement(name = "minDateTimeIndex")
    private String minDateTimeIndex;

    // 曲线的最大时间索引
    @XmlElement(name = "maxDateTimeIndex")
    private String maxDateTimeIndex;
}
