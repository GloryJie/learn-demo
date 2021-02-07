package top.gloryjie.learn.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author Jie
 * @since 2021/1/26
 */
public class GroupChatClient {

    private String host;

    private int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 使用自带的编解码器，保持和server段的一致
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 添加自定义的Handler，处理服务器的消息
                            pipeline.addLast(new GroupChatClientHandler());
                        }
                    });
            // 同步进行连接
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()){
                // 读取控制台输入
                String msg = scanner.nextLine();
                // 使用channel发送
                channel.writeAndFlush(msg + "\r\n");
            }

        }finally {
            bossGroup.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws Exception {
        GroupChatClient groupChatClient = new GroupChatClient("127.0.0.1", 8000);
        groupChatClient.start();
    }
}
