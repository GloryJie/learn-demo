package top.gloryjie.learn.nio.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author Jie
 * @since 2021/1/17
 */
public class NIOClientDemo {

    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        socketChannel.connect(new InetSocketAddress("127.0.0.1", 6666));

        while (!socketChannel.finishConnect()){
            System.out.println("连接未完成，稍作等待.....");
            TimeUnit.MICROSECONDS.sleep(100);
        }

        String msg = "hello, NIO server";
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));

        // 向channel写入数据
        socketChannel.write(byteBuffer);

        TimeUnit.MINUTES.sleep(3);

    }

}
