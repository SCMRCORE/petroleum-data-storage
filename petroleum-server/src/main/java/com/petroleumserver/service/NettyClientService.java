package com.petroleumserver.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

public interface NettyClientService {

    /**
     * 向服务端发起连接请求
     */
    void connect(Bootstrap bootstrap, int retry);

    /**
     * 发送登录数据包
     * @param channel
     */
    void sendLoginPacket(Channel channel);

    /**
     * 最终启动客户端方法
     */
    void startClient();

}
