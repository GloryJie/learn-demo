package top.gloryjie.learn.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Jie
 * @since 2021/1/26
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // channelGroup 用来维护所有建立连接的channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    // Gets called after the {@link ChannelHandler} was added to the actual context and it's ready to handle events.
    // 当前Handler加入到对应的pipeline时会调用
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        String msg = "当前用户【%s】已准备好Handler，即将上线";
        System.out.println(String.format(msg, ctx.channel().remoteAddress().toString()));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String msg = "当前用户【%s】已移除Handler，彻底下线了";
        System.out.println(String.format(msg, ctx.channel().remoteAddress().toString()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String upMsg = "【用户：%s】【上线了】\n";
        String msg = String.format(upMsg, ctx.channel().remoteAddress().toString());
        System.out.println(msg);
        // 先向其他的channel即群员发送当前用户的上线消息
        channelGroup.writeAndFlush(msg);
        // 然后将当前channel加入channelGroup中进行维护
        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String upMsg = "【用户：%s】【下线了】\n";
        String msg = String.format(upMsg, ctx.channel().remoteAddress().toString());
        // 先向其他的channel即群员发送当前用户的上线消息
        channelGroup.writeAndFlush(msg);
        System.out.println("当前用户数：" + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel currentChannel = ctx.channel();
        String content = "【客户端：%s 说】：%s\n";
        String currentSay = "【自己：%s 说】：%s\n";
        channelGroup.forEach(channel -> {
            if (channel == currentChannel) {
                // 如果是自己，则发送回去进行回显
                currentChannel.writeAndFlush(String.format(currentSay, currentChannel.remoteAddress().toString(), msg));
            } else {
                // 向其他的channel进行广播
                channel.writeAndFlush(String.format(content, currentChannel.remoteAddress().toString(), msg));
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
