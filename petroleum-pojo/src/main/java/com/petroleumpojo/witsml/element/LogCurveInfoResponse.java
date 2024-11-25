package com.petroleumpojo.witsml.element;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data  
@XmlRootElement(name = "logCurveInfo")  
@XmlAccessorType(XmlAccessType.FIELD)  
public class LogCurveInfoResponse {

    @XmlElement(name = "uid")  
    
    private String uid;

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

    @XmlElement(name = "columnIndex")  
    
    private int columnIndex;

    @XmlElement(name = "traceState")  
    
    private String traceState;

    @XmlElement(name = "typeLogData")  
    
    private String typeLogData;

    @XmlElement(name = "sensorOffset")  
    
    private SensorOffset sensorOffset;

    
    @Data  
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SensorOffset {

        @XmlElement(name = "uom")  
        
        private String uom;

        @XmlElement(name = "value")  
        
        private int value;
    }
}