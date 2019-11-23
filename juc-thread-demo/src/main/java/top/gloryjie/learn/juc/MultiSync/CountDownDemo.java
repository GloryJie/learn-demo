package top.gloryjie.learn.juc.MultiSync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 主线程等待多个线程步调一致问题
 * @author jie
 * @since 2019/5/30
 */
public class CountDownDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownDemo downDemo = new CountDownDemo();

//        downDemo.modeOne();

        downDemo.modeThree();
    }


    /**
     * 原始的线程模型
     * @throws InterruptedException
     */
    public void modeOne() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mode1 Thread 1 work done");
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mode1 Thread 2 work done");
        });

        // 启动线程
        thread1.start();
        thread2.start();
        // 主线程等待t1和t2结束
        thread1.join();
        thread2.join();

        System.out.println("mode1 two thread had done");
    }


    /**
     * 使用CountDownLatch来简化
     * @throws InterruptedException
     */
    public void modeTwo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mode2 Thread 1 work done");
            // 完成计数减1
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mode2 Thread 2 work done");
            latch.countDown();
        }).start();

        // 主线程等待上面两个线程工作完成, 即计数减为0
        latch.await();

        System.out.println("mode2 two thread had done");
    }


    /**
     * 使用线程池优化
     * @throws InterruptedException
     */
    public void modeThree() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mode3 Thread 1 work done");
            // 计数器减1
            latch.countDown();
        });

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("mode3 Thread 2 work done");
            latch.countDown();
        });

        // 主线程等待上面两个任务工作完成
        latch.await();

        System.out.println("mode3 two thread had done");
        executor.shutdown();
    }
}
