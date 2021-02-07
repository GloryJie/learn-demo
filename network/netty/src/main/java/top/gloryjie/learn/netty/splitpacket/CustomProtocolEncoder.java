package top.gloryjie.learn.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Jie
 * @since 2021/1/30
 */
public class CustomProtocolEncoder extends MessageToByteEncoder<CustomProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf out) throws Exception {
        System.out.println("encode方法被调用");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }

}
