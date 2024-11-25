package com.petroleumserver.wits0client.protocol.request;

import com.petroleumserver.wits0client.protocol.Packet;
import lombok.Data;

import static com.petroleumserver.wits0client.protocol.command.Command.LOGOUT_REQUEST;

@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
