package top.gloryjie.learn.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author Jie
 * @since 2020/6/28
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪时会回调此方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty client say hello to server!", CharsetUtil.UTF_8));
        System.out.println("send to server completed");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf  = (ByteBuf) msg;
        System.out.println("client receive from server: " + byteBuf.toString(StandardCharsets.UTF_8));
        System.out.println("server address: " + ctx.channel().remoteAddress().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
