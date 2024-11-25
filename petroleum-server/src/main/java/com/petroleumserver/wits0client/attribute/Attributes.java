package com.petroleumserver.wits0client.attribute;

import com.petroleumserver.wits0client.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
 
    
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}