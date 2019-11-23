package top.gloryjie.learn.juc.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 延迟 + 回调 + 重试
 *
 * @author jie
 * @since 2019/11/18
 */
@Slf4j
public class ScheduleRetryExecutor {


    private static final ScheduledExecutorService SCHEDULE_EXECUTOR;

    static {
        int corePoolSize = 1;
        ThreadFactory threadFactory = new ScheduleThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        SCHEDULE_EXECUTOR = new ScheduledThreadPoolExecutor(corePoolSize, threadFactory, handler);
    }


    /**
     * 延迟 + 回调的封装
     * @param task 待执行的任务
     * @param delay 延迟时间
     * @param unit 时间单位
     * @param <T> 任务的返回结果
     * @return CompletableFuture实例
     */
    public static <T> CompletableFuture<T> schedule(Supplier<T> task, long delay, TimeUnit unit){
        CompletableFuture<T> future = new CompletableFuture<>();
        SCHEDULE_EXECUTOR.schedule(()->{
            try {
                future.complete(task.get());
            }catch (Throwable throwable){
                future.completeExceptionally(throwable);
            }
        }, delay, unit);
        return future;
    }

    /**
     * 重试延迟任务, 此处使用thenApply, 执行回调和执行任务为同一线程
     * @param task 待执行的任务, 返回true标识任务结束,不需要重试
     * @param delay 延迟时间
     * @param unit 时间单位
     */
    public static void retrySchedule(Supplier<Boolean> task, long delay, TimeUnit unit){
        schedule(task, delay, unit).thenApply(result ->{
            System.out.println("回调执行线程: " + Thread.currentThread().getName());
            if (!result){
               // 任务不成功, 再次放入队列重新执行
               retrySchedule(task, delay, unit);
           }
           return null;
        }).exceptionally(throwable -> {
            log.error("任务发生异常", throwable);
            return null;
        });
    }

    static class ScheduleThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix = "Schedule-thread-";

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, namePrefix + threadNumber.getAndIncrement());

        }
    }




    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);

        ScheduleRetryExecutor.retrySchedule(()->{
            System.out.println("任务执行线程: " + Thread.currentThread().getName());
            int times = count.getAndIncrement();
            if (times < 5){
                System.out.println(String.format("任务执行的第%s次", times));
                return false;
            }
            System.out.println("任务结束");
            return true;
        }, 2, TimeUnit.SECONDS);

    }


}
