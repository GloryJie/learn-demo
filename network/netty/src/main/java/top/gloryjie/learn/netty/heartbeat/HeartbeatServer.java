package top.gloryjie.learn.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Jie
 * @since 2021/1/27
 */
public class HeartbeatServer {

    private int port;

    public HeartbeatServer(int port) {
        this.port = port;
    }

    public void start()throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 增加编解码器
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 使用netty自带的IdleStateHandler来加查读写空闲，会向后面的Handler出发事件
                            pipeline.addLast(new IdleStateHandler(5,8,15, TimeUnit.SECONDS));
                            // 添加自定义Handler，处理IdleStateHandler的事件，并接受心跳包
                            pipeline.addLast(new HeartbeatServerHandler(3));
                        }
                    });
            // 绑定端口并监听操作，同步
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            // 同步监听serverChannel的close事件，main线程会阻塞在这里
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        HeartbeatServer heartbeatServer = new HeartbeatServer(8000);
        heartbeatServer.start();
    }
}
