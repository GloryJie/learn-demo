package top.gloryjie.learn.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * 回复同样的内容
 * @author Jie
 * @since 2020/6/28
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据的事件回调，可在该方法内读取客户端发送过来的数据
     * @param ctx 上下文对象，可以获取channel，pipeline等
     * @param msg 客户端发送过来的数据，通常是ByteBuf的一个实例
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server received content: " + byteBuf.toString(StandardCharsets.UTF_8));
        System.out.println("client address: " + ctx.channel().remoteAddress().toString());
    }

    /**
     * 数据读取完毕后的回调方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~~~".getBytes(StandardCharsets.UTF_8)));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
