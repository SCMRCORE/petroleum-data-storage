package com.petroleumserver.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

public interface NettyClientService {

     
    void connect(Bootstrap bootstrap, int retry);

     
    void sendLoginPacket(Channel channel);

     
    void startClient();

}
