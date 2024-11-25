package com.petroleumserver.wits0client.protocol.response;

import com.petroleumserver.wits0client.protocol.Packet;
import lombok.Data;

import static com.petroleumserver.wits0client.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {
 
    private String message;
 
    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}