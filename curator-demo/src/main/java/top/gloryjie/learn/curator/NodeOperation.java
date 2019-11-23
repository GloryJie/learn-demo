package top.gloryjie.learn.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;


/**
 * 节点增删改查操作
 *
 * @author jie
 * @since 2019/7/3
 */
public class NodeOperation {

    private CuratorFramework client;


    public NodeOperation(CuratorFramework client) {
        this.client = client;
    }

    public void create(String path, String data) throws Exception {
        // 可以使用CreateMode枚举来直接节点类型
        client.create()
                // 若父节点不存在则创建
                .creatingParentsIfNeeded()
                // 持久节点模式
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, data.getBytes());
    }


    public void delete(String path) throws Exception {
        client.delete()
                //保证删除, 只要会话有效, 会不断舱室知道删除成功
                .guaranteed()
                // 递归删除节点
                .deletingChildrenIfNeeded()
                .forPath(path);
    }

    public byte[] getData(String path) throws Exception {

        Stat stat = new Stat();
        return client.getData()
                // 获取数据时还可以顺便获取Stat
                .storingStatIn(stat)
                .forPath(path);
    }

    public void setNewData(String path, String data) throws Exception {
        client.setData()
                .forPath(path, data.getBytes());
    }

    public void close(){
        client.close();
    }

}
