package top.gloryjie.learn.java.base.juc.future;

import java.util.concurrent.*;

/**
 * @author jie
 * @since 2019/5/31
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

        FutureTask<Integer> futureTask = new FutureTask<>(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                System.out.println("task been interrupted");
            }
            return 3;
        });

        executorService.execute(futureTask);

        Integer result = futureTask.get();

        // 此处Future的实现就是FutureTask
        Future<String> future = executorService.submit(() -> {
            return "result";
        });

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
