package top.gloryjie.learn.java.base.juc.MultiSync;

import java.util.concurrent.*;

/**
 * @author jie
 * @since 2019/5/30
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        demo.modeOne();
    }


    public void modeOne() {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // 因为CyclicBarrier是同步执行回调函数才执行唤醒线程,所以此处使用线程池来执行回调任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("回调线程: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            executor.execute(() -> System.out.println("combine product with component 1 and 2 done"));
            throw new RuntimeException("模拟异常");
        });

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("build component 1 done");
                    cyclicBarrier.await();
                    TimeUnit.SECONDS.sleep(2);
                    // 只有cyclicBarrier 为0 时,才会执行下面的代码
                    System.out.println("clean component 1 done");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
//                    break;
                }
            }
        }, "Build-Thread-1").start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println("build component 2 done");
                    cyclicBarrier.await();
                    TimeUnit.SECONDS.sleep(2);

                    // 只有cyclicBarrier 为0 时,才会执行下面的代码
                    System.out.println("clean component 2 done");

                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
//                    break;
                }
            }
        }, "Build-Thread-2").start();
    }
}
