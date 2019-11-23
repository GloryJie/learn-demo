package top.gloryjie.learn.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import top.gloryjie.learn.curator.util.CuratorClientFactory;

import java.util.concurrent.TimeUnit;

/**
 * 可重入分布式锁, 使用InterProcessMutex
 * @author jie
 * @since 2019/7/6
 */
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            CuratorFramework client = CuratorClientFactory.generate("127.0.0.1:2181");

            InterProcessLock lock = new InterProcessMutex(client,"/jr/lock");

            try {
                lock.acquire();
                System.out.println("thread1 - 获取锁成功");
                TimeUnit.MINUTES.sleep(1);
            } catch (Exception e) {
                System.out.println("thread1 -  获取锁失败");
                e.printStackTrace();
            }finally {
                try {
                    lock.release();
                    System.out.println("thread1 - 释放锁成功");
                } catch (Exception e) {
                    System.out.println("thread1 - 释放锁失败");
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            CuratorFramework client = CuratorClientFactory.generate("127.0.0.1:2181");

            InterProcessLock lock = new InterProcessMutex(client,"/jr/lock");

            try {
                lock.acquire();
                System.out.println("thread2 - 获取锁成功");
                TimeUnit.MINUTES.sleep(1);
            } catch (Exception e) {
                System.out.println("thread2 - 获取锁失败");
                e.printStackTrace();
            }finally {
                try {
                    lock.release();
                    System.out.println("thread2 - 释放锁成功");
                } catch (Exception e) {
                    System.out.println("thread2 - 释放锁失败");
                    e.printStackTrace();
                }
            }
        }).start();



        TimeUnit.MINUTES.sleep(10);
    }




}
