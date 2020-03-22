package top.gloryjie.learn.java.base.jvm.gc.demo03;

/**
 * 模拟YoungGC后因Survivor存放不下存活对象而进入老年代的场景
 *
 * @author jie
 * @since 2020/3/12
 */
public class GCDemo03 {

    public static final int ONE_M = 1024 * 1024;
    public static final int ONE_K = 1024;

    /**
     * -XX:InitialHeapSize=20m        => 初始堆大小20M
     * -XX:MaxHeapSize=20m            => 最大堆大小20M
     * -XX:NewSize=10m                => 新生堆大小5M
     * -XX:MaxNewSize=10m             => 最大新生堆大小5M
     * -XX:SurvivorRation=8           => Eden:Survivor = 8:1, 即Eden=8M, Survivor=1M
     * -XX:PretenureSizeThreshold=10m => 大对象10M
     * -XX:MaxTenuringThreshold=15    => 指定年龄边界15
     * -XX:UseParNewGC                => 新生代使用ParNew
     * -XX:UseConcMarkSweepGC         => 老年代使用CMS
     * <p>
     * -XX:+PrintGCDetails            => 打印GC详细信息
     * -XX:+PrintGCTimeStamps         => 打印发生GC时的时间戳
     * -Xloggc:GCDemo03.log           => 将gc日志写入GCDemo03.log文件
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * ONE_M];
        array1 = new byte[2 * ONE_M];
        // array1未释放引用
        array1 = new byte[2 * ONE_M];

        byte[] array2 = new byte[256 * ONE_K];
        array2 = null;

        // 触发回收, 此时有4m+256k垃圾, 2m(array1)+300k未知存活
        byte[] array3 = new byte[2 * ONE_M];
        // 回收后, 因2m(array1) > 1m(s区大小)而进入老年代
        // 300k未知进入s区
    }
}
