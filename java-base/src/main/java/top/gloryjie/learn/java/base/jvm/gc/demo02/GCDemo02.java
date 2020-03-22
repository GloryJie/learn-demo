package top.gloryjie.learn.java.base.jvm.gc.demo02;

/**
 * 模拟YoungGC, 触发动态年龄判断而进入老年代的场景
 *
 * @author jie
 * @since 2020/3/12
 */
public class GCDemo02 {

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
     * -XX:+PrintTenuringDistribution => 打印Survivor区域的年龄分布
     * -Xloggc:GCDemo02.log           => 将gc日志写入GCDemo02.log文件
     *
     * @param args
     */
    public static void main(String[] args) {
        // 内存分配: Eden=8m, Survivor=1m, old=10m

        byte[] array1 = new byte[2 * ONE_M];
        array1 = new byte[2 * ONE_M];
        array1 = new byte[2 * ONE_M];
        array1 = null;

        // 分配个256k, 则会 256k(array2) + 300k(未知) > survivor/2 成立
        byte[] array2 = new byte[256 * ONE_K];

        // 此时内存中有6m垃圾, 256k(array2) + 300k(未知) 存活
        // 若需要再分配2m的空间, 则会因内存分配失败触发垃圾回收
        byte[] array3 = new byte[2 * ONE_M];

        // 若将array2移到此处会是怎样？？
        //byte[] array2 = new byte[256 * ONE_K];

        // 第一次回收后, Eden中有2m(array3), from survivor有256k(array2) + 300k(未知)
        // 虽然Survivor中存活对象已经大于 50% , 此时并不会触发动态年龄判断, 而是收入Survivor

        array3 = new byte[2 * ONE_M];
        array3 = new byte[2 * ONE_M];
        array3 = new byte[256 * ONE_K];
        array3 = null;

        // 此时内存中有6m+256k垃圾, 256k(array2) + 300k(未知) 存活
        // 触发第二次回收
        byte[] array4 = new byte[2 * ONE_M];

        // 回收后, 因256k(array2) + 300k(未知) 存活 > 512k(Survivor的一般), 则触发动态年龄判断进入老年代
        // 此时Eden有2m(array4), survivor均为0, old有256k(array2) + 300k(未知)
    }
}
