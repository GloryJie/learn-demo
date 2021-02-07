package top.gloryjie.learn.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author Jie
 * @since 2021/1/27
 */
public class HeartbeatServerHandler extends SimpleChannelInboundHandler<String> {

    private int readerIdleCount = 0;

    private int maxIdleCount = 3;

    public HeartbeatServerHandler() {
    }

    public HeartbeatServerHandler(int maxIdleCount) {
        this.maxIdleCount = maxIdleCount;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("[server receive msg]: " + msg);
        // 心跳包
        if ("hb".equals(msg)) {
            readerIdleCount = 0;
            ctx.writeAndFlush("ok");
        } else if ("close".equals(msg)) {
            System.out.println("客户端主动断开连接， 服务端也断开");
            ctx.close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(String.format("【channel: %s】【上线了】", ctx.channel().remoteAddress()));
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String eventLogContent = null;

            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    eventLogContent = "读空闲";
                    readerIdleCount++;
                    break;
                case WRITER_IDLE:
                    eventLogContent = "写空闲";
                    break;
                case ALL_IDLE:
                    eventLogContent = "读写空闲";
                default:
                    break;
            }

            String logMsg = "【channel: %s】发生超时事件【%s】";
            System.out.println(String.format(logMsg, ctx.channel().remoteAddress(), eventLogContent));

            // 超过次数则主动断开连接
            if (readerIdleCount > maxIdleCount) {
                String tipMsg = "【channel: %s】读空闲超过了【%s次】，服务端主动断开其连接";
                System.out.println(String.format(tipMsg, ctx.channel().remoteAddress(), maxIdleCount));
                ctx.writeAndFlush("close");
                ctx.close();
            }

        } else {
            System.out.println("未知事件： " + evt);
        }
    }
}
