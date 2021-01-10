package top.gloryjie.learn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Jie
 * @since 2020/6/28
 */
public class NettyServerDemo {

    private int port;

    public NettyServerDemo(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        EchoServerHandler serverHandler = new EchoServerHandler();

        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        }  finally {
            eventExecutors.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        int port = 9090;

        NettyServerDemo serverDemo = new NettyServerDemo(port);
        try {
            serverDemo.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
