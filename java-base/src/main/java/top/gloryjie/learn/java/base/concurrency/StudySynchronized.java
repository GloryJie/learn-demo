package top.gloryjie.learn.java.base.concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2020/5/5
 */
public class StudySynchronized {


    /**
     * 探究使用wait, notify死锁的场景
     */
    @Test
    public void testWaitNotifyDeadLock() throws InterruptedException {
        final List<Integer> queue = new ArrayList<>();

        Runnable producer = () -> {
            while (true) {
                synchronized (queue) {
                    System.out.println(Thread.currentThread().getName() + ", 获得了锁");
                    while (queue.size() == 1) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ", 条件不足, wait");
                            queue.wait();
                            System.out.println(Thread.currentThread().getName() + ", 从wait处获得了锁");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    queue.add(1);
                    System.out.println(Thread.currentThread().getName() + ", 生产者生产了一条。");
                    queue.notify();
                    // queue.notifyAll();
                    System.out.println(Thread.currentThread().getName() + ", 执行了notify");

                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                synchronized (queue) {
                    System.out.println(Thread.currentThread().getName() + ", 获得了锁");
                    while (queue.size() == 0) {
                        try {
                            System.out.println(Thread.currentThread().getName() + ", 条件不足, wait");
                            queue.wait();
                            System.out.println(Thread.currentThread().getName() + ", 从wait处获得了锁");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.remove(0);
                    System.out.println(Thread.currentThread().getName() + ", 消费者消费了一条。");
                    queue.notify();
                    // queue.notifyAll();
                    System.out.println(Thread.currentThread().getName() + ", 执行了notify");
                }
            }
        };

        new Thread(consumer, "C1").start();
        new Thread(consumer, "C2").start();
        // 保证上面的先执行
        TimeUnit.SECONDS.sleep(1);

        new Thread(producer, "P1").start();

        TimeUnit.MINUTES.sleep(10);
    }

}



