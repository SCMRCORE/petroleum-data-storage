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

    
    @XmlElement(name = "uidWell")
    private String uidWell;

    
    @XmlElement(name = "uidWellbore")
    private String uidWellbore;

    
    @XmlElement(name = "uid")
    private String uid;

    
    @XmlElement(name = "nameWell")
    private String nameWell;

    
    @XmlElement(name = "nameWellbore")
    private String nameWellbore;

    
    @XmlElement(name = "name")
    private String name;

    
    @XmlElement(name = "objectGrowing")
    private String objectGrowing;

    
    @XmlElement(name = "serviceCompany")
    private String serviceCompany;

    
    @XmlElement(name = "runNumber")
    private String runNumber;

    
    @XmlElement(name = "indexType")
    private String indexType;

    
    @XmlElement(name = "startDateTimeIndex")
    private String startDateTimeIndex;

    
    @XmlElement(name = "endDateTimeIndex")
    private String endDateTimeIndex;

    
    @XmlElement(name = "indexCurve")
    private String indexCurve;

    
    @XmlElement(name = "nullValue")
    private String nullValue;    

    
    @XmlElement(name = "logData")
    private LogData logData;
}
