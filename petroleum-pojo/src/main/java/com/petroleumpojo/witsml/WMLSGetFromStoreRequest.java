package com.petroleumpojo.witsml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 作为soap请求的请求对象
 */
@Data
@XmlRootElement(name = "WMLS_GetFromStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class WMLSGetFromStoreRequest {
    // 字段名需要符合xml请求接口一致
    private String WMLtypeIn;
    private String QueryIn;
}
