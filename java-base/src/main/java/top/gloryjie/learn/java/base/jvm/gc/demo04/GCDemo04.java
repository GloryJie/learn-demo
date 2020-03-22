package top.gloryjie.learn.java.base.jvm.gc.demo04;

/**
 * 模拟触发OldGC
 *
 * @author jie
 * @since 2020/3/12
 */
public class GCDemo04 {

    public static final int ONE_M = 1024 * 1024;
    public static final int ONE_K = 1024;


    /**
     * -XX:InitialHeapSize=20m        => 初始堆大小20M
     * -XX:MaxHeapSize=20m            => 最大堆大小20M
     * -XX:NewSize=10m                => 新生堆大小10M
     * -XX:MaxNewSize=10m             => 最大新生堆大小10M
     * -XX:SurvivorRation=8           => Eden:Survivor = 8:1, 即Eden=8M, Survivor=1M
     * -XX:PretenureSizeThreshold=3m  => 大对象3M
     * -XX:MaxTenuringThreshold=15    => 指定年龄边界15
     * -XX:UseParNewGC                => 新生代使用ParNew
     * -XX:UseConcMarkSweepGC         => 老年代使用CMS
     * <p>
     * -XX:+PrintGCDetails            => 打印GC详细信息
     * -XX:+PrintGCTimeStamps         => 打印发生GC时的时间戳
     * -Xloggc:gc.log                 => 将gc日志写入gc.log文件
     *
     * @param args
     */
    public static void main(String[] args) {
        // 大对象直接进入老年代
        byte[] array1 = new byte[4 * ONE_M];
        array1 = null;

        // 未释放
        byte[] array2 = new byte[2 * ONE_M];
        byte[] array3 = new byte[2 * ONE_M];
        byte[] array4 = new byte[2 * ONE_M];
        byte[] array5 = new byte[128 * ONE_K];

        // 此时有6m+128k存活在Eden, 4m存活在老年代
        // 触发回收
        byte[] array6 = new byte[2 * ONE_M];
    }
}
