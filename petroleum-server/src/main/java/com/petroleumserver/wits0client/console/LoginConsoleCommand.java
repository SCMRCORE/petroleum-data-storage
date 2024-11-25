package com.petroleumserver.wits0client.console;

import com.petroleumserver.wits0client.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("请输入用户名: ");
        loginRequestPacket.setUsername(scanner.nextLine());
        System.out.print("请输入密码: ");
        loginRequestPacket.setPassword(scanner.nextLine());

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
    }

}
