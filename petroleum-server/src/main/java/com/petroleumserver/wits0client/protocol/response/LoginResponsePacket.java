package com.petroleumserver.wits0client.protocol.response;

import com.petroleumserver.wits0client.protocol.Packet;
import lombok.Data;

import static com.petroleumserver.wits0client.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
