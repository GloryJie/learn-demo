package top.gloryjie.learn.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * hdfs command operation demo
 *
 * @author Jie-R 1620
 * @since 1.0
 */
public class HdfsFileSystemOperationDemo {

    public static Configuration configuration;

    public static URI hdfsUri;

    public static FileSystem fileSystem;

    @BeforeClass
    public static void init() throws Exception {
        configuration = new Configuration();
        configuration.set("dfs.replication", "1");

        hdfsUri = new URI("hdfs://hadoop-001:8020");
        fileSystem = FileSystem.get(hdfsUri, configuration, "vagrant");
    }

    @Test
    public void listTest() throws Exception {
        Path path = new Path("/");
        RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(path, true);
        while (iterator.hasNext()) {
            final LocatedFileStatus next = iterator.next();
            System.out.println(next.getPath().toUri().toString());
        }
    }

    @Test
    public void existsCommand() throws Exception {
        Path path = new Path("/jojo/hello.txt");
        final boolean exists = fileSystem.exists(path);

        System.out.println("exists result: " + exists);
    }

    @Test
    public void mkdirCommand() throws Exception {
        Path path = new Path("/jojo");

        final boolean result = fileSystem.mkdirs(path);

        System.out.println("mkdir result: " + result);
    }

    @Test
    public void createCommand() throws Exception {
        Path path = new Path("/jojo/hello.txt");

        try (FSDataOutputStream os = fileSystem.create(path)) {
            os.writeBytes("jojojojojojojojojojojoj");
        }
    }


    @Test
    public void appendCommand() throws Exception {
        Path path = new Path("/jojo/hello.txt");

        try (FSDataOutputStream os = fileSystem.append(path)) {
            os.writeBytes("jojo2");
        }
    }

    @Test
    public void readCommand() throws Exception {
        Path path = new Path("/jojo/hello.txt");

        try (FSDataInputStream is = fileSystem.open(path)) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            IOUtils.copyBytes(is, os, 1024);

            System.out.println(os.toString());
        }
    }

    @Test
    public void delCommand() throws Exception {
        Path path = new Path("/jojo/hello.txt");
        fileSystem.delete(path, false);
    }

}
