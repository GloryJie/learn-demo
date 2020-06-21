package top.gloryjie.learn.java.base.concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * AQS 共享模式的典型实现, debug了解共享模式对于资源的传播行为
 *
 * @author jie
 * @since 2020/6/7
 */
public class StudySemaphore {

    @Test
    public void testSemaphore() throws InterruptedException {

        // 可运行2个线程同时执行
        Semaphore semaphore = new Semaphore(2);

        Runnable runnable = () -> {
            semaphore.acquireUninterruptibly();
            try {

                System.out.println(Thread.currentThread().getName() + "获取到资源");

                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };


        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(runnable, "线程" + i));
        }

        threadList.forEach(Thread::start);

        TimeUnit.MINUTES.sleep(1);
    }


}
