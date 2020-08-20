package top.gloryjie.learn.juc.future;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author jie
 * @since 2019/5/31
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFutureDemo demo = new CompletableFutureDemo();

//        demo.modeOne();
//        demo.modeTwo();
//        demo.modeThree();

//        CompletableFuture<String> test = CompletableFuture.completedFuture("jojo");
//        CompletableFuture<String> apply = test.thenApply(i -> {
//            System.out.println("execute apply: " + i);
//            return "jojo2";
//        });

        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            list.add(CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "jojo: " + finalI;
            }));
        }

        CompletableFuture<Void> all = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]));
        try {
            all.get(3, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<String> resultList = new ArrayList<>();
        list.forEach(task ->{
            if (task.isDone()){
                String result = task.getNow("");
                if (!result.equals("")){
                    resultList.add(result);
                }
            }
            task.thenAcceptAsync(s -> {
                System.out.println("放入缓存：" + s);
            });
        });

        System.out.println("result: " + resultList.toString());


        TimeUnit.SECONDS.sleep(10);
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
