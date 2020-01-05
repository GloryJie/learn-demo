package top.gloryjie.learn.cache.guava;

import com.google.common.cache.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2020/1/1
 */
public class GuavaCacheDemo {

    @Test
    public void simpleCache() {
        // 向使用map一样使用
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10).build();

        cache.put("java", "hello world");

        String java = cache.getIfPresent("java");

        Assert.assertNotNull(java);
    }


    @Test
    public void expireCache() throws InterruptedException {
        // 向使用map一样使用
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10).expireAfterWrite(3, TimeUnit.SECONDS).build();

        cache.put("java", "hello world");

        TimeUnit.SECONDS.sleep(3);

        String java = cache.getIfPresent("java");
        Assert.assertNull(java);
    }


    @Test
    public void dynamicLoadCache() throws ExecutionException {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10).build();

        String java = cache.get("java", () -> "java");

        Assert.assertEquals(java, "java");
    }


    @Test
    public void cacheLoader() throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10).build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s;
            }
        });

        String java = cache.get("java");

        Assert.assertEquals(java, "java");
    }


    @Test
    public void cacheWithRemoveListener() throws ExecutionException, InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .removalListener((RemovalListener<String, String>) removalNotification ->
                        System.out.println(String.format("key=%s, value=%s 被移除, 原因%s", removalNotification.getKey(), removalNotification.getValue(), removalNotification.getCause().name())))
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build();

        cache.put("java", "java");
        cache.put("go", "go");

        cache.invalidate("java");

        TimeUnit.SECONDS.sleep(5);

    }


}
