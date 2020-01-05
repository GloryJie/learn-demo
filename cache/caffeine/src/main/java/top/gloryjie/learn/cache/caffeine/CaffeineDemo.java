package top.gloryjie.learn.cache.caffeine;

import com.github.benmanes.caffeine.cache.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author jie
 * @since 2020/1/1
 */
public class CaffeineDemo {

    @Test
    public void simpleCache() {
        Cache<String, String> cache = Caffeine.newBuilder().maximumSize(10L).build();

        cache.put("java", "java");

        String java = cache.getIfPresent("java");
        String go = cache.get("go", s -> s);

        Assert.assertNotNull(java);
        Assert.assertNotNull(go);

        cache.invalidate("go");
        Assert.assertNull(cache.getIfPresent("go"));
    }


    @Test
    public void loadingCache() {
        LoadingCache<String, String> cache = Caffeine.newBuilder().maximumSize(10).build(s -> s);

        String result = cache.get("java");

        Assert.assertEquals("java", result);
    }

    @Test
    public void asyncCache() {
        AsyncCache<String, String> cache = Caffeine.newBuilder().maximumSize(10).buildAsync();

        CompletableFuture<String> java = cache.get("java", s -> {
            throw new RuntimeException(s);
        });

        java.thenAccept(System.out::println);

        java.thenApply(s -> s + " hello world").thenAccept(System.out::println);
    }

    @Test
    public void asyncLoadingCache() {
        AsyncLoadingCache<String, String> cache = Caffeine.newBuilder().maximumSize(10).buildAsync(s -> s);

        CompletableFuture<String> java = cache.get("java");

        java.thenAccept(System.out::println);
        java.thenApply(s -> s + " hello world").thenAccept(System.out::println);

    }


}
