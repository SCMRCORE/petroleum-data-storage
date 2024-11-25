package com.petroleumpojo.witsml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

 
@Data
@XmlRootElement(name = "WMLS_GetFromStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class WMLSGetFromStoreRequest {
    
    private String WMLtypeIn;
    private String QueryIn;
}
