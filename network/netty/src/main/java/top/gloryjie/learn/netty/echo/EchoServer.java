package top.gloryjie.learn.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Jie
 * @since 2020/6/28
 */
public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        // EventLoopGroup内部具有selector实例，内部封装和实现了轮询和派发事件的逻辑
        // bossGrouper监听并处理连接事件
        EventLoopGroup bossGrouper = new NioEventLoopGroup();
        // workerGrouper监听并处理读写事件的
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGrouper, workerGroup) // 指定两个工作组
                    .channel(NioServerSocketChannel.class) // 指定channel的实现类型
                    .localAddress(new InetSocketAddress(port)) // 执行监听的本地地址
                    .option(ChannelOption.SO_BACKLOG, 128) // 指定队列大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 保持活动连接的状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 自定义Handler加入到pipeline中
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            // 绑定端口
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGrouper.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        int port = 9090;

        try {
            EchoServer serverDemo = new EchoServer(port);
            serverDemo.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
