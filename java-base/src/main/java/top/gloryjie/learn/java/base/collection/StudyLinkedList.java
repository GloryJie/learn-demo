package top.gloryjie.learn.java.base.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author jie
 * @since 2020/4/15
 */
public class StudyLinkedList {


    /**
     * 添加操作测试
     */
    @Test
    public void addOperateTest() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        // 同add()
        linkedList.addLast("d");
        // 添加到第一个
        linkedList.addFirst("a");

        linkedList.add(0, "1");

        linkedList.addAll(Arrays.asList("e", "f", "g"));

        System.out.println(linkedList);
    }

    @Test
    public void removeOperateTest() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");

        linkedList.remove();
    }
}
