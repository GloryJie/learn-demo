package top.gloryjie.learn.curator.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * @author jie
 * @since 2019/7/6
 */
public class CuratorClientFactory {

    private CuratorClientFactory() {
    }

    public static CuratorFramework generate(String url){
        RetryPolicy retryPolicy = new RetryNTimes(3, 3000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(url)
                .retryPolicy(retryPolicy)
                .build();

        client.start();
        System.out.println("zk连接成功");
        return client;
    }

}
