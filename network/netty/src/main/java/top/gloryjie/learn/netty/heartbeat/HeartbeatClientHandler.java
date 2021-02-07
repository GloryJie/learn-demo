package top.gloryjie.learn.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Jie
 * @since 2021/1/27
 */
public class HeartbeatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client receive msg : " + msg);
        if ("close".equals(msg)){
            System.out.println("服务器主动断开连接，客户端也断开");
            ctx.close();
        }
    }
}
