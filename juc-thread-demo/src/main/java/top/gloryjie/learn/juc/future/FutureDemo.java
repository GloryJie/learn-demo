package top.gloryjie.learn.juc.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2019/5/30
 */
public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("task had been interrupted");
            }
            return 1 + 2;
        });

        try {
            TimeUnit.SECONDS.sleep(1);
            // 判断任务是否已经取消
            System.out.println("task is canceled: " + future.isCancelled());

            System.out.println("task cancel result: " + future.cancel(true));

            System.out.println("task is done? " + future.isDone());

            // 获取任务返回结果, 阻塞式获取
            System.out.println("task execute result: " + future.get());
            System.out.println("task cancel result: " + future.cancel(true));

            // 判断任务是否已经完成
            System.out.println("task is done? " + future.isDone());
            System.out.println("task is canceled: " + future.isCancelled());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
