package com.petroleumserver.service.lmp;

import com.petroleumcommom.properties.NettyProperties;
import com.petroleumserver.service.NettyClientService;
import com.petroleumserver.wits0client.codec.Spliter;
import com.petroleumserver.wits0client.handler.*;
import com.petroleumserver.wits0client.protocol.request.LoginRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class NettyClientServiceImpl implements NettyClientService {

    @Resource
    NettyProperties nettyProperties;

    public Channel channel;

    @Override
    public void startClient() {
        // 作为nio处理事件的线程池
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            // 用于启动netty
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true) // 启动心跳机制
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 50) // 设置连接超时时长为50s

                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 空闲检测
                            ch.pipeline().addLast(new IMIdleStateHandler());
                            ch.pipeline().addLast(new Spliter());
                            // ...单例模式，多个 channel 共享同一个 handler
                            ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                            // 登录响应处理器
                        ch.pipeline().addLast(new LoginResponseHandler());
                            // 收消息处理器
                            ch.pipeline().addLast(MessageResponseHandler.INSTANCE);
                            // 登出响应处理器
//                        ch.pipeline().addLast(LogoutResponseHandler.INSTANCE);
                            // 心跳定时器
                            ch.pipeline().addLast(new HeartBeatTimerHandler());
                        }
                    });
            int retry = nettyProperties.getMAX_RETRY();
            connect(bootstrap, retry);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void connect(Bootstrap bootstrap, int retry) {
        // 发起异步请求连接服务端
        bootstrap.connect(nettyProperties.getHost(), nettyProperties.getPort())
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        channel = (Channel) future.channel(); // 得到连接成功channel
                        sendLoginPacket(channel); // 发送登l的数据包
                        // 如果超时
                    } else if(retry == 0) {
                        // 重试次数用完
                        log.info("连接wits0服务端失败。");
                    } else {
                        int order = (nettyProperties.getMAX_RETRY() - retry) + 1;
                        // 指数回退策略重试逻辑
                        int delay = 1 << order;
                        log.info("wits0服务端连接失败这是第{}次连接.....", retry);
                        System.err.println();
                        bootstrap.config().group().schedule(() -> connect(bootstrap, retry - 1), delay, TimeUnit.SECONDS);
                    }
        });

    }

    @Override
    public void sendLoginPacket(io.netty.channel.Channel channel) {
        // 发送登录数据包
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(nettyProperties.getUsername());
        loginRequestPacket.setPassword(nettyProperties.getPassword());
        channel.writeAndFlush(loginRequestPacket);
    }
}
