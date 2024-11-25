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

    
    @XmlElement(name = "data")
    private String data;

}
