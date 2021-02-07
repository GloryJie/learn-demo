package top.gloryjie.learn.nio.bytebuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件映射mmap的简单demo
 * @author Jie
 * @since 2021/1/15
 */
public class MappedByteBufferDemo {

    public static void main(String[] args) {
        String path = "/Users/nuc/workspace/mygithub/learn-demo/network/nio/src/main/resources/fileChannelDemo.txt";

        try {
            File file = new File(path);
            if (!file.exists()){
                System.out.println("文件路径错误： " + path);
            }
            // 指定读写模式
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            // 获取对应文件的通道
            FileChannel fileChannel = randomAccessFile.getChannel();
            // 从通道获取内存文件的映射
            // 参数1：映射的模式
            // 参数2：映射的文件起始位置
            // 参数3：从起始位置开始映射的字节数
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 10);

            // 将hello修改成全大写
            mappedByteBuffer.put((byte) 'H');
            mappedByteBuffer.put((byte) 'E');
            mappedByteBuffer.put((byte) 'L');
            mappedByteBuffer.put((byte) 'L');
            mappedByteBuffer.put((byte) 'O');

            fileChannel.force(true);
            randomAccessFile.close();
            fileChannel.close();

            System.out.println("文件修改成功~~~");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
