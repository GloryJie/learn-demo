package top.gloryjie.learn.java.base.jvm.tool;

import java.util.concurrent.TimeUnit;

/**
 * 测试使用jstat工具, 观察新生代的回收
 *
 * @author jie
 * @since 2020/3/15
 */
public class JstatDemo {

    /**
     * 执行参数
     * -XX:InitialHeapSize=200m
     * -XX:MaxHeapSize=200m
     * -XX:NewSize=100m
     * -XX:MaxNewSize=100m
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=15
     * -XX:PretenureSizeThreshold=3m
     * -XX:+UseParNewGC
     * -XX:+ConcMarkSweepGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCTimeStamps
     * -Xloggc:JstatDemo.log
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(30);
        while (true) {
            loadData();
        }
    }

    /**
     * 一秒分配50m内存
     *
     * @throws Exception
     */
    public static void loadData() throws Exception {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            // 分片100kb内存
            data = new byte[100 * 1024];
        }
        TimeUnit.SECONDS.sleep(1);
    }
}
