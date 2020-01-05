package top.gloryjie.learn.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 缓存核心三个类
 * CacheManager
 * Cache
 * Element
 *
 * @author jie
 * @since 2019/12/25
 */
public class EhcacheDemo {

    static CacheManager cacheManager;

    @BeforeClass
    public static void init() {
        // 通过配置生成CacheManager, 一般全局唯一
        URL resource = EhcacheDemo.class.getClassLoader().getResource("ehcache.xml");
        cacheManager = CacheManager.create(resource);
    }

    @AfterClass
    public static void destroy() {
        cacheManager.shutdown();
    }

    /**
     * 简单使用
     *
     * @throws InterruptedException
     */
    @Test
    public void simpleCache() throws InterruptedException {
        // 获取配置的Cache实例
        Cache userCache = cacheManager.getCache("userCache");

        // 往缓存写入数据
        IntStream.range(0, 100).forEach(i -> {
            int num = ThreadLocalRandom.current().nextInt(1000);
            Element element = new Element(i, "学生" + num);
            userCache.put(element);
        });

        // 获取缓存的数据
        Element element = userCache.get(5);

        System.out.println(element);
        Assert.assertNotNull(element);
        Assert.assertEquals(element.getObjectKey(), 5);
    }


    @Test
    public void dataExpireTest() throws InterruptedException {
        // 获取配置的Cache实例
        Cache userCache = cacheManager.getCache("userCache");

        // 往缓存写入数据
        IntStream.range(0, 100).forEach(i -> {
            int num = ThreadLocalRandom.current().nextInt(1000);
            Element element = new Element(i, "学生" + num);
            userCache.put(element);
        });

        TimeUnit.SECONDS.sleep(2);

        userCache.get(2);

        TimeUnit.SECONDS.sleep(5);

        Assert.assertNull(userCache.get(2));
    }

    @Test
    public void propertyChangeListenerTest() throws InterruptedException {
        // 获取配置的Cache实例
        Cache userCache = cacheManager.getCache("userCache");

        // 添加元素移除监听器
        userCache.addPropertyChangeListener(evt ->
                System.out.println(
                        String.format("%s 由 %s 变到了 %s", evt.getPropertyName(), evt.getOldValue(), evt.getNewValue())));

        // 往缓存写入数据
        IntStream.range(0, 100).forEach(i -> {
            int num = ThreadLocalRandom.current().nextInt(1000);
            Element element = new Element(i, "学生" + num);
            userCache.put(element);
        });

        Element element = new Element(5, "change");
        userCache.put(element);

        TimeUnit.SECONDS.sleep(10);

    }


    @Test
    public void persistentCacheTest() throws InterruptedException {
        // 获取配置的Cache实例
        Cache persistentCache = cacheManager.getCache("persistentCache");

        // 往缓存写入数据
        persistentCache.put(new Element("123", "123"));
        persistentCache.flush();

        // 重启读取数据
//        Element element = persistentCache.get("123");
    }


}
