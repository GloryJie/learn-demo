package top.gloryjie.learn.nio.bytebuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author jie
 * @since 2021/1/10
 */
public class FileChannelDemo {

    public static void main(String[] args) {
        String path = "/Users/nuc/workspace/mygithub/learn-demo/network/nio/src/main/resources/fileChannelDemo.txt";
        String content = "hello, FileChannel, 你好呀, 文件管道";
        String copyPath = "/Users/nuc/workspace/mygithub/learn-demo/network/nio/src/main/resources/fileChannelDemoCopy.txt";
        writeContent(content, path);

        System.out.println(readContent(path));

        copyFile(path, copyPath);

        System.out.println(readContent(copyPath));

    }

    public static void copyFile(String sourcePath, String destPath) {
        try {
            File sourceFile = new File(sourcePath);
            File destFile = new File(destPath);

            if (!sourceFile.exists() || !sourceFile.isFile()) {
                return;
            }

            if (destFile.exists()) {
                if (!destFile.isFile()) {
                    return;
                }
            } else {
                destFile.createNewFile();
            }

            FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
            FileChannel destChannel = new FileOutputStream(destFile).getChannel();

            // 零拷贝复制
            // sourceChannel.transferTo(0, sourceChannel.size(), destChannel);
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readContent(String path) {
        try {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                FileChannel fileChannel = fis.getChannel();

                long fileSize = fileChannel.size();

                ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) fileSize);

                // 通过channel将文件数据读入byteBuffer中
                fileChannel.read(byteBuffer);

                fis.close();
                fileChannel.close();

                // 切换模式
                byteBuffer.flip();

                // array方法对应的实现不一定会存在
                // return new String(byteBuffer.array());

                Charset charset = StandardCharsets.UTF_8;
                CharBuffer charBuffer = charset.decode(byteBuffer);
                return charBuffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void writeContent(String content, String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                if (!file.isFile()) {
                    return;
                }
            } else {
                file.createNewFile();
            }
            if (file.exists() && file.isFile()) {
                FileOutputStream fos = new FileOutputStream(file);
                FileChannel fileChannel = fos.getChannel();
                byte[] data = content.getBytes(StandardCharsets.UTF_8);
                ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);

                // 填入数据进byteBuffer
                byteBuffer.put(data);

                // 将写模式切换成读模式,由channel进行读取
                byteBuffer.flip();

                // 通过channel读取byteBuffer的数据写入文件中
                fileChannel.write(byteBuffer);

                // 关闭流和channel
                fos.close();
                fileChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
