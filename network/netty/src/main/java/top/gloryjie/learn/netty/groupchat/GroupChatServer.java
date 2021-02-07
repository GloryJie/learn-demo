package top.gloryjie.learn.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author Jie
 * @since 2021/1/26
 */
public class GroupChatServer {

    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    public void start() throws Exception{
        // bossGroup处理连接事件
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // workerGroup 处理channel的读写事件
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        // 下面是netty的服务器配置
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childOption(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 使用netty自带的编解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            // 自定义Handler，接受群聊消息并转发
                            pipeline.addLast(new GroupChatServerHandler());
                        }
                    });
            // 同步监听端口
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            // 同步监听ServerSocketChannel的关闭，也就是main线程会阻塞在这里
            channelFuture.channel().closeFuture().sync();
        }finally {
            // 同步关闭两个工作组
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        GroupChatServer groupChatServer = new GroupChatServer(8000);
        groupChatServer.start();
    }
}
