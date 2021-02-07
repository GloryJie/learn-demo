package top.gloryjie.learn.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 协议的解码器
 * @author Jie
 * @since 2021/1/30
 */
public class CustomProtocolDecoder extends ByteToMessageDecoder {

    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("当前数据长度: " + in.readableBytes());
        if (in.readableBytes() >= 4){
            if (length == 0){
                length = in.readInt();
            }

            if (in.readableBytes() < length){
                System.out.println("读取的数据字节长度不够，继续等待。。。。");
                return;
            }

            byte[] data = new byte[length];
            if (in.readableBytes() >= length){
                // 将数据从byteBuf中读取到data字节数组
                in.readBytes(data);

                // 封装成自定义协议的类型中
                CustomProtocol customProtocol = new CustomProtocol();
                customProtocol.setLength(length);
                customProtocol.setContent(data);

                // 添加到输出集合中，会自动出发下一个inboundHandler
                out.add(customProtocol);

                // 重置length
                length = 0;
            }
        }

    }
}
