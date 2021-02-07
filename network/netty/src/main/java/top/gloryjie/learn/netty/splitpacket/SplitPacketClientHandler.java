package top.gloryjie.learn.netty.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * @author Jie
 * @since 2021/1/30
 */
public class SplitPacketClientHandler extends SimpleChannelInboundHandler<CustomProtocol> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i=0;i<100;i++){
            byte[] data = ("水果" + i+ "非常好吃").getBytes(StandardCharsets.UTF_8);
            ctx.writeAndFlush(new CustomProtocol(data.length, data));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol msg) throws Exception {
        System.out.println("客户端接收到了服务端的数据：" + new String(msg.getContent(), StandardCharsets.UTF_8));
    }
}
