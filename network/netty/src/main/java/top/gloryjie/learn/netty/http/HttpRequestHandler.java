package top.gloryjie.learn.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jie
 * @since 2021/1/24
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取http请求的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("ChannelHandlerContext的实际类型： " + ctx.getClass());
        if (msg instanceof HttpRequest){
            System.out.println("msg的类型：" + msg.getClass());
            System.out.println("http客户端地址：" + ctx.channel().remoteAddress().toString());

            ByteBuf responseContent = Unpooled.copiedBuffer("从netty处理的http协议的服务器返回的内容", StandardCharsets.UTF_8);

            // 构造http响应内容
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, responseContent);
            httpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
            httpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, responseContent.readableBytes());

            // 将封装的http响应内容写入返回
            ctx.writeAndFlush(httpResponse);
        }
    }

}
