package top.gloryjie.learn.juc.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2019/5/31
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureDemo demo = new CompletableFutureDemo();

//        demo.modeOne();

//        demo.modeTwo();

        demo.modeThree();
    }


    /**
     * 异步任务串行执行
     */
    public void modeOne() {
        // 将任务串行组织起来
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> "Hello ") // 启动异步任务一
                .thenApply(s -> s + "world") // 任务二
                .thenApply(String::toUpperCase); // 任务三

        // 获取异步任务结果
        System.out.println("task result: " + task.join());
    }


    /**
     * 汇聚关系, 即等待任务完成后再执行相应动作
     */
    public void modeTwo() {
        // 异步任务1
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task1 working");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("task1 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "component1";
        });

        // 异步任务2
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task2 working");
                TimeUnit.SECONDS.sleep(4);
                System.out.println("task2 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "component2";
        });

        // 汇聚任务1和2结果, 形成任务3
        CompletableFuture<String> task3 = task1.thenCombine(task2, (v1, v2) -> {
            System.out.println("receive task1 result: " + v1);
            System.out.println("receive task2 result: " + v2);
            System.out.println("ready to combine component");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "product complete";
        });

        // 等待并获取任务3结果
        System.out.println(task3.join());
    }

    public void modeThree() throws ExecutionException, InterruptedException {
        // 异步任务1
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task1 working");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("task1 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "component1";
        });

        // 异步任务2
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("task2 working");
                TimeUnit.SECONDS.sleep(4);
                System.out.println("task2 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "component2";
        });

        // 汇聚任务1和2的结果, 只要有一个任务执行完就执行任务3
        CompletableFuture<Void> task3 = task1.acceptEitherAsync(task2, (v) -> {
            System.out.println("receive task return: " + v);
        });

        task3.join();

        TimeUnit.MINUTES.sleep(1);
//        System.out.println(task2.get());
    }
}
