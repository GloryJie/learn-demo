package top.gloryjie.learn.juc.future;

import java.util.concurrent.*;

/**
 * @author jie
 * @since 2019/5/31
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> task = ()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                System.out.println("task been interrupted");
            }
            return 3;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(task);


        executorService.submit(futureTask);


        try {
            TimeUnit.SECONDS.sleep(1);
            futureTask.cancel(true);
            if (futureTask.isCancelled()){
                System.out.println("task cancelled");
            }else {
                System.out.println("task execute result: " + futureTask.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }


    }
}
