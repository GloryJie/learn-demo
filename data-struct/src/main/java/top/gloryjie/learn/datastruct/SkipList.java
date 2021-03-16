package top.gloryjie.learn.datastruct;

import java.util.Random;

/**
 * 跳表的实现
 *
 * @author jie
 * @since 2021/3/14
 */
public class SkipList {

    /**
     * 最大层数, 第0层为完整的数据链
     */
    private static final int MAX_LEVEL = 16;

    /**
     * 当前跳表的最大层数
     */
    private int levelCount = 1;

    /**
     * 带头链表, 头结点具有完整的MAX_LEVEL层, 即16层
     */
    private Node head = new Node(MAX_LEVEL);
    /**
     * 随机器
     */
    private Random random = new Random();

    /**
     * 跳表的节点，每个节点记录了当前节点数据和所在层数数据
     */
    public class Node {
        private int data = -1;

        /**
         * forwards[i] 表示当前索引层的下一个节点
         * forwards[0] 为完整的数据层, 表示下一个节点
         * forwards[3]表示当前节点在第三层的下一个节点。
         */
        private Node forwards[];

        /**
         * 这个值其实可以不用，看优化insert()
         */
        private int maxLevel = 0;

        public Node(int level) {
            forwards = new Node[level];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    /**
     * 目的: 生成一个小于MAX_LEVEL的随机数
     * 随机 level 次，如果是奇数层数 +1，防止伪随机
     *
     * @return
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public void insert(int value) {
        int level = head.forwards[0] == null ? 1 : randomLevel();
        // 每次只增加一层，如果条件满足
        if (level > levelCount) {
            level = ++levelCount;
        }

        Node newNode = new Node(level);
        newNode.data = value;

        // 用来存储需要更新的层次合适位置的前一个节点
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        Node p = head;
        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            // 找到第i层最后一个比value小的节点, 也就是第一个比value大的前一个节点
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
            }
            // 并不是没有节点都有相同的层次, 所以需要加上判断
            // levelCount 会 > level，所以加上判断
            if (level > i) {
                update[i] = p;
            }
        }

        // 将当前节点加入到对应的索引层的链路中, i=0即为数据链路
        for (int i = 0; i < level; ++i) {
            // 相当于在 update[i] 和 update[i].forward[i] 中插入一个节点
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        // 更新层高
        if (levelCount < level) levelCount = level;
    }


    /**
     * 查找操作
     *
     * @param value
     * @return
     */
    public Node find(int value) {
        Node p = head;
        // 从最大层开始查找，找到第i层的第一个大于value的前一个节点
        // 然后通过--i，移动到下层再细化查找
        // 当i等于0时, p刚好是完整数据链路中,第一个不小于value的节点, 有可能是等于value
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
            }
        }

        // 判断是否等于value, 而确定是否存在
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }


    /**
     * 删除操作
     * 需要将每一层中等于value的节点都删除掉
     *
     * @param value
     */
    public void delete(int value) {
        // 需要更新的层次节点
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // p节点刚好是目的节点,则进行update中每一个层次的删除
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                // 删除每一层中等于value的节点
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }

    /**
     * 打印每个节点数据和最大层数
     */
    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    /**
     * 打印所有数据
     */
    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.forwards;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].data : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].forwards)[i] != null);
            System.out.println();
            d = c;
        }
    }


    public static void main(String[] args) {
        SkipList list2 = new SkipList();
        list2.insert(1);
        list2.insert(2);
        list2.insert(6);
        list2.insert(7);
        list2.insert(8);
        list2.insert(3);
        list2.insert(4);
        list2.insert(5);
        System.out.println();
        list2.printAll_beautiful();


    }


}
