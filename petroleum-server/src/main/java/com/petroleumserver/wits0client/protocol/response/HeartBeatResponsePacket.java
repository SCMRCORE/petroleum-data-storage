package com.petroleumserver.wits0client.protocol.response;


import com.petroleumserver.wits0client.protocol.Packet;

import static com.petroleumserver.wits0client.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
