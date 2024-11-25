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

    
    @XmlElement(name = "mnemonic")
    private String mnemonic;

    
    @XmlElement(name = "classWitsml")
    private String classWitsml;

    
    @XmlElement(name = "unit")
    private String unit;

    
    @XmlElement(name = "mnemAlias")
    private String mnemAlias;

    
    @XmlElement(name = "nullValue")
    private String nullValue;

    
    @XmlElement(name = "minDateTimeIndex")
    private String minDateTimeIndex;

    
    @XmlElement(name = "maxDateTimeIndex")
    private String maxDateTimeIndex;
}
