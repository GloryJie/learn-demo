package top.gloryjie.learn.java.base.concurrency;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * debug研究ConcurrentHashMap
 * @author Jie
 * @since 2020/7/21
 */
public class StudyCHMap {

    /**
     * 初始化
     */
    @Test
    public void initTableTest(){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        // 放入第一个元素才会进行初始化
        map.put("jojo", "jojo");
    }

    /**
     * 扩容
     */
    @Test
    public void expansionSizeTest(){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

        IntStream.range(0, 11).forEach(i -> map.put(UUID.randomUUID().toString(), String.valueOf(i)));

        map.put("jojo", "jojo");
    }
}
