package top.gloryjie.learn.java.base.jvm.gc.demo01;

/**
 * 模拟YoungGC的场景,查看gc.log
 *
 * @author jie
 * @since 2020/3/12
 */
public class GCDemo01 {
    public static final int ONE_M = 1024 * 1024;

    /**
     * -XX:InitialHeapSize=10m        => 初始堆大小10M
     * -XX:MaxHeapSize=10m            => 最大堆大小10M
     * -XX:NewSize=5m                 => 新生堆大小5M
     * -XX:MaxNewSize=5m              => 最大新生堆大小5M
     * -XX:SurvivorRation=8           => Eden:Survivor = 8:1, 即Eden=4M, Survivor=0.5M
     * -XX:PretenureSizeThreshold=10m => 大对象10M
     * -XX:UseParNewGC                => 新生代使用ParNew
     * -XX:UseConcMarkSweepGC         => 老年代使用CMS
     * <p>
     * -XX:+PrintGCDetails            => 打印GC详细信息
     * -XX:+PrintGCTimeStamps         => 打印发生GC时的时间戳
     * -Xloggc:GCDemo01.log           => 将gc日志写入GCDemo01.log文件
     *
     * @param args
     */
    public static void main(String[] args) {
        // 内存分配: Eden=8m, Survivor=1m, old=10m
        byte[] array1 = new byte[2 * ONE_M];
        array1 = new byte[2 * ONE_M];
        array1 = new byte[2 * ONE_M];
        array1 = null;

        // 此时堆内存中有在6m垃圾, 加上一些未知的对象(大约在300k左右)

        // 若需要再分配2m的空间, 则会因内存分配失败触发垃圾回收
        byte[] array2 = new byte[2 * ONE_M];

        // 而到此时, array1 产生的垃圾已经被回收. 而array2所需要的2m内存也已经分配在堆中
        // 所以此时 Eden会有2m占用, from Survivor = 300k(未知对象)
    }
}
