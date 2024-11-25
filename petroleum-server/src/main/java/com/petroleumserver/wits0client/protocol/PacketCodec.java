package com.petroleumserver.wits0client.protocol;

import com.petroleumserver.wits0client.protocol.request.HeartBeatRequestPacket;
import com.petroleumserver.wits0client.protocol.request.LoginRequestPacket;
import com.petroleumserver.wits0client.protocol.request.LogoutRequestPacket;
import com.petroleumserver.wits0client.protocol.request.MessageRequestPacket;
import com.petroleumserver.wits0client.protocol.response.HeartBeatResponsePacket;
import com.petroleumserver.wits0client.protocol.response.LoginResponsePacket;
import com.petroleumserver.wits0client.protocol.response.LogoutResponsePacket;
import com.petroleumserver.wits0client.protocol.response.MessageResponsePacket;
import com.petroleumserver.wits0client.serialize.Serializer;
import com.petroleumserver.wits0client.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.petroleumserver.wits0client.protocol.command.Command.*;

public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x12671721;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodec() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
        packetTypeMap.put(HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }


    public Packet decode(ByteBuf byteBuf) {
        
        byteBuf.skipBytes(4);

        
        byteBuf.skipBytes(1);

        
         
        byte serializeAlgorithm = byteBuf.readByte();

        
        byte command = byteBuf.readByte();

        
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
