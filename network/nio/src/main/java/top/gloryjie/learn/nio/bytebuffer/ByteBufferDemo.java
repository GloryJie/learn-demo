package top.gloryjie.learn.nio.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author Jie
 * @since 2021/1/15
 */
public class ByteBufferDemo {

    public static void main(String[] args) {
        // 可以创建两种类型的ByteBuffer：HeapByteBuffer、DirectByteBuffer
        // 主要区别是堆内内存和堆外内存，HeapByteBuffer底层是数组实现
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        // 当前是写模式
        byteBuffer.put((byte) 1);
        byteBuffer.putShort((short) 2);
        byteBuffer.putInt(3);
        byteBuffer.putLong(4L);
        byteBuffer.putFloat(5.5f);
        byteBuffer.putDouble(6.6);
        byteBuffer.putChar('A');

        // 将写模式切换成读模式
        byteBuffer.flip();

        // 怎么放入就要怎么读取，一定要按照顺序
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getFloat());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
    }


    public static void simpleUse(){

    }
}
