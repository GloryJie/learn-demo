package top.gloryjie.learn.nio.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Jie
 * @since 2021/1/17
 */
public class NIOServerDemo {

    public static void main(String[] args) throws Exception {

        // 创建Selector，用于监听事件的产生
        Selector selector = Selector.open();

        // 创建一个ServerSocketChannel，用于监听端口处理连接请求
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);

        // 将serverSocketChannel 交给selector进行监听其连接事件
        // 向selector注册后，会返回一个SelectionKey的令牌
        SelectionKey serverSocketChannelSelectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 开始处理selector监听到事件
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("等待了1s暂无事件发生");
                continue;
            }

            // select() 过后，产生的事件包装成SelectionKey放入集合中
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                // 说明是连接事件, 可以通过serverSocketChannel来创建该连接对应的SocketChannel
                if (selectionKey.isAcceptable()) {
                    // accept只有serverSocketChannel才能监听
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 和serverSocketChannel保持一致，非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 新创建的连接，同样需要让selector监听其产生的时间，关注读事件。并绑定一个ByteBuffer，后续可通过attachment获取
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("创建连接成功，并放入selector进行监听读事件");
                } else if (selectionKey.isReadable()) {
                    // 当前可读时间
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 上述向selector注册时，attach的一个ByteBuffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
                    System.out.println("从连接中读取到：" + charBuffer.toString());
                }

                // 事件处理完毕，从当前事件集合中移除
                iterator.remove();
            }
        }


    }
}
