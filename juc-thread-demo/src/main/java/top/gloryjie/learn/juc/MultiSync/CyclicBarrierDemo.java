package top.gloryjie.learn.juc.MultiSync;

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

        // 因为CyclicBarrier是同步调用回调函数才执行唤醒线程,所以此处使用线程池来执行回调任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () ->
                executor.execute(() -> System.out.println("combine product with component 1 and 2 done")));

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("build component 1 done");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println("build component 2 done");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}
