package com.petroleumserver.wits0client.attribute;

import com.petroleumserver.wits0client.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

//    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    // 登录标记
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}