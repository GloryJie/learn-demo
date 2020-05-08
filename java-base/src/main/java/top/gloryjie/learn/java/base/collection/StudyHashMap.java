package top.gloryjie.learn.java.base.collection;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author jie
 * @since 2020/4/23
 */
public class StudyHashMap {


    /**
     * 探究HashMap.hash()方法优化
     * 将高16位和低16位进行异或, 降低哈希碰撞的概率
     */
    @Test
    public void testHash() {
        int test = 851975;
        printMode(16, test);
        printMode(16, 851959);
        printMode(16, hash(test));
        printMode(16, hash(851959));

        System.out.println("*****resize*******");
        System.out.println(hash(test));
        System.out.println(hash(851958));

        printBin(hash(test));
        printBin(hash(851958));

        printMode(16, hash(test));
        printMode(16, hash(851958));

        printBin(15);
//
//        printBin(hash(test));
//        printBin(hash(851959));

    }


    /**
     * 探究tableSizeFor
     * 寻找大于cap的最小2^n
     */
    @Test
    public void testTableSizeFor() {
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(13));
    }


    @Test
    public void testPutMethod() {
        HashMap<String, String> map = new HashMap<>();

        map.put("a", "a");

    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static void printBin(int h) {
        System.out.println(String.format("%32s", Integer.toBinaryString(h)).replace(' ', '0'));
    }

    static void printMode(int size, int num) {
        System.out.println((size - 1) & num);
    }

    static int tableSizeFor(int cap) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
