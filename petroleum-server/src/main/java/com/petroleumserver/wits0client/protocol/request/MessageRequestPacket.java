package com.petroleumserver.wits0client.protocol.request;

import com.petroleumserver.wits0client.protocol.Packet;
import lombok.Data;

import static com.petroleumserver.wits0client.protocol.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {
 
    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}