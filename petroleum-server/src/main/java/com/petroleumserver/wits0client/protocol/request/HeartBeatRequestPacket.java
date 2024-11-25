package com.petroleumserver.wits0client.protocol.request;


import com.petroleumserver.wits0client.protocol.Packet;

import static com.petroleumserver.wits0client.protocol.command.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
