package top.gloryjie.learn.java.base.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jie
 * @since 2020/3/30
 */
public class StudyArrayList {

    /**
     * 研究ArrayList类的构造方法
     */
    @Test
    public void testDeclare() throws Exception {

        // 研究原因: c.toArray might (incorrectly) not return Object[] (see 6260652)
        // 大多数容器底层存储都用的Object[], 简单方便
        // Arrays.ArrayList.toArray() 方法就是特例, 其内部数组存放是具有类型的 E[]
        List<String> strList = Arrays.asList("123");
        Object[] strObjects = strList.toArray();

        System.out.println(strObjects.getClass().getComponentType());

        // 此处会报错, 因为strObjects为[Ljava.lang.String;类型, 存放内容为String
        // strObjects[0] = new Object();

        // debug 构造方法
        // 其内部对于返回的是非 Object[] 类型的数组, 会使用拷贝将其转换成Object[]
        ArrayList<String> list = new ArrayList<>(strList);
    }


    /**
     * 测试指定零值的情况
     */
    @Test
    public void testConstructorInitialZeroValue() {
        // debug, capacity = 0
        ArrayList<String> list = new ArrayList<>(0);

        // debug, initial Collection.size = 0
        ArrayList<String> list2 = new ArrayList<>(new ArrayList<>());
    }


    @Test
    public void testGrow() {
//        int MAX_ARRAY_SIZE = Integer.MAX_VALUE -8;
//        int minCapacity = Integer.MIN_VALUE;
//        int old = Integer.MAX_VALUE >> 1;
//        int newCapacity = old + (old >> 1);
//
//        if (newCapacity - minCapacity < 0)
//            newCapacity = minCapacity;
//        if (newCapacity - MAX_ARRAY_SIZE > 0)
//            System.out.println(13);

        int len = Integer.MAX_VALUE - 200;
        int min = len + 300;
        System.out.println(min);
        System.out.println(min - len);
//        System.out.println(Integer.MAX_VALUE + 1 == Integer.MIN_VALUE);

    }


    @Test
    public void testRemove() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

//        list.removeAll(Arrays.asList("a", "b"));
//        list.retainAll(Arrays.asList("a", "b"));
        list.removeIf(i -> i.equals("b"));
    }

    @Test
    public void testReplace() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.set(0, "A");
    }

    @Test
    public void test() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        for (String s : list) {
            if (s.equals("a")) {
                list.remove(s);
            }
        }


    }

}
