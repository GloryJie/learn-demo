package top.gloryjie.learn.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;


/**
 * @author jie
 * @since 2019/7/2
 */
public class AppDemo {

    public static void main(String[] args) throws InterruptedException {
        String url = "127.0.0.1:2181";

        RetryPolicy retryPolicy = new RetryNTimes(3, 3000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(url)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        NodeOperation operation = new NodeOperation(client);

        String nodePath = "/jr/path";

        String data = "data";

        try {
            operation.create(nodePath, data);
            System.out.println("创建节点成功");
        } catch (Exception e) {
            System.out.println("创建节点异常: " + e.getMessage());
        }

        try {
            String temp = new String(operation.getData(nodePath));
            System.out.println("获取的节点数据: " + temp);
        } catch (Exception e) {
            System.out.println("获取节点数据异常: " + e.getMessage());
        }

        try {
            operation.setNewData(nodePath,"new" + data);
            System.out.println("修改节点数据成功");
        } catch (Exception e) {
            System.out.println("修改节点数据异常: " + e.getMessage());
        }

        try {
            operation.delete(nodePath);
            System.out.println("删除节点数据成功");
        } catch (Exception e) {
            System.out.println("删除节点数据异常: " + e.getMessage());
        }

        operation.close();
        System.out.println("关闭操作");

    }
}
