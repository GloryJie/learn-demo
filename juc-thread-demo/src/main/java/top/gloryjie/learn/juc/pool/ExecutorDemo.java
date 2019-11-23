package top.gloryjie.learn.juc.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2019/5/30
 */
public class ExecutorDemo {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(1, 4, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task " + finalI +" done");
            });

        }
    }
}
