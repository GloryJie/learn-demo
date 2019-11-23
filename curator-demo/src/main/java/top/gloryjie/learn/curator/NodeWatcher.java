package top.gloryjie.learn.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2019/7/3
 */
public class NodeWatcher {


    public static void main(String[] args) throws Exception {
        String url = "127.0.0.1:2181";

        RetryPolicy retryPolicy = new RetryNTimes(3, 3000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(url)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        NodeCache nodeCache = new NodeCache(client, "/jr/learn");

        nodeCache.getListenable().addListener(() -> {
            ChildData childData = nodeCache.getCurrentData();
            if (childData.getData() == null) {
                System.out.println("节点数据为空");
            } else {
                System.out.println("节点发生变化: " + new String(childData.getData()));
            }
        });

        nodeCache.start();

        TimeUnit.MINUTES.sleep(10);


    }
}
