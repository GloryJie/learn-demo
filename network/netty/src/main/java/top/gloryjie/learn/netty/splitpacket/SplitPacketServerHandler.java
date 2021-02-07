package top.gloryjie.learn.netty.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * @author Jie
 * @since 2021/1/30
 */
public class SplitPacketServerHandler extends SimpleChannelInboundHandler<CustomProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol msg) throws Exception {
        System.out.println("收到客户端发送的数据：" + new String(msg.getContent(), StandardCharsets.UTF_8));
    }
}
