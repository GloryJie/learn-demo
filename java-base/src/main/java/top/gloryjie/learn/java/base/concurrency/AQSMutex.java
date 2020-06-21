package top.gloryjie.learn.java.base.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Aqs实现互斥锁, 用来debug跟踪
 *
 * @author jie
 * @since 2020/5/31
 */
public class AQSMutex {

    @Test
    public void testAqsMutex() throws InterruptedException {

        Mutex mutex = new Mutex();

        Runnable runnable = () -> {
            mutex.acquire(1);
            System.out.println(Thread.currentThread().getName() + " 获得了锁");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mutex.release(1);
                System.out.println(Thread.currentThread().getName() + " 释放了锁");
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(runnable, "线程" + i).start();
        }

        // 等待,避免直接结束
        TimeUnit.MINUTES.sleep(1);
    }


    static class Mutex extends AbstractQueuedSynchronizer {


        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }


}
