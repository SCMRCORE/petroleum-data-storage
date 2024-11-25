package com.petroleumserver.wits0client.console;

import com.petroleumserver.wits0client.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToServerConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给服务端：");
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(message));
    }
}
