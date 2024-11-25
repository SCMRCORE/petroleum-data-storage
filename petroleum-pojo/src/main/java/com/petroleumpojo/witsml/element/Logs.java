package com.petroleumpojo.witsml.element;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

 
@Data
@XmlRootElement(name = "logs")
@XmlAccessorType(XmlAccessType.FIELD)
public class Logs {
    @XmlElement(name = "log")
    List<Log> logList;

}
