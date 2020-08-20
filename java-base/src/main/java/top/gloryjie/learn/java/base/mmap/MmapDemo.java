package top.gloryjie.learn.java.base.mmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author Jie
 * @since 2020/7/12
 */
public class MmapDemo {

    public static final int FILE_MAX_SIZE = 1024 * 10; // 10k

    public static void main(String[] args) throws Exception {
        File file = new File("mmap.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        // 建立映射
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, FILE_MAX_SIZE);

        byte[] data = "Hello NIO MappedByteBuffer".getBytes(StandardCharsets.UTF_8);

        ByteBuffer writeBuffer = mappedByteBuffer.slice();
        writeBuffer.put(data);

        mappedByteBuffer.position(data.length);
        ByteBuffer readBuffer = mappedByteBuffer.slice();
        readBuffer.flip();
        readBuffer.limit(data.length);

        byte[] readData = new byte[data.length];

        readBuffer.get(readData);
        System.out.println(new String(readData,StandardCharsets.UTF_8));
    }

    public static void putData(MappedByteBuffer mappedByteBuffer){
        mappedByteBuffer.putInt(1);
        mappedByteBuffer.putInt(2);
        mappedByteBuffer.putInt(3);
    }
}
