package top.gloryjie.learn.netty.splitpacket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Jie
 * @since 2021/1/30
 */
public class SplitPacketClient {

    private String host;

    private int port;

    public SplitPacketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start()throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 自定义协议的编解码器
                            pipeline.addLast("encoder", new CustomProtocolEncoder());
                            pipeline.addLast("decoder", new CustomProtocolDecoder());

                            // 自定义协议的处理
                            pipeline.addLast("customProtocolHandler", new SplitPacketClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

        }finally {
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        SplitPacketClient client = new SplitPacketClient("127.0.0.1", 9000);
        client.start();
    }
}
