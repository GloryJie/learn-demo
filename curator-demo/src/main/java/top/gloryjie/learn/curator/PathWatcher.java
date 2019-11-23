package top.gloryjie.learn.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2019/7/3
 */
public class PathWatcher {

    public static void main(String[] args) throws Exception {
        String url = "127.0.0.1:2181";

        RetryPolicy retryPolicy = new RetryNTimes(3, 3000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(url)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        PathChildrenCache pathCache = new PathChildrenCache(client,"/jr", true);

        pathCache.getListenable().addListener((client1, event) -> {
            System.out.println("事件类型: " + event.getType());
            for (ChildData datum : pathCache.getCurrentData()) {
                System.out.println("节点: " + datum.getPath() + ", 数据: " + new String(datum.getData()));
            }
        });

        pathCache.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
