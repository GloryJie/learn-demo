package top.gloryjie.learn.hadoop.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author jie
 * @since 2020/1/30
 */
public class WordCountLocalApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        System.setProperty("HADOOP_USER_NAME", "vagrant");

        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS","hdfs://hadoop-001:8020");

        // 任务描述,配置
        Job job = Job.getInstance(configuration);

        // 设置主类
        job.setJarByClass(WordCountLocalApp.class);

        // 配置mapper, reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 配置mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 配置reducer(最后的结果)输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        // 配置输入输出源
        FileInputFormat.addInputPath(job, new Path("hadoop/hdfs/src/main/resources/input"));
        FileOutputFormat.setOutputPath(job, new Path("hadoop/hdfs/src/main/resources/output"));

        // 等待任务运行结束
        boolean result = job.waitForCompletion(true);


        System.exit(result ? 0 : -1);
    }
}
