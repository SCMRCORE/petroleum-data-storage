package com.petroleumpojo.witsml.element;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@Data
@XmlRootElement(name = "logData")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogData {

    // 包含日志的具体数据（例如：深度、时间等）
    @XmlElement(name = "data")
    private String data;

}
