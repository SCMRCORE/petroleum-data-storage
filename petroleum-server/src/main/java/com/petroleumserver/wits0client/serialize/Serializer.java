package com.petroleumserver.wits0client.serialize;


import com.petroleumserver.wits0client.serialize.impl.JSONSerializer;

public interface Serializer {

     
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();
 
     
    byte getSerializerAlgorithm();
    
     
    byte[] serialize(Object object);
 
     
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}