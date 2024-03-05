package top.gloryjie.learn.java.base.juc.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author jie
 * @since 2019/6/1
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        // 创建分治任务的线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        // 创建任务
        Fibonacci fibonacci = new Fibonacci(5);

        // 启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);

        System.out.println("result: " + result);

    }

    static class Fibonacci extends RecursiveTask<Integer> {

        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            // 创建子任务
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();

            // 创建子任务2
            Fibonacci f2 = new Fibonacci(n - 2);

            // 等待子任务结果, 并合并结果
            return f2.compute() + f1.join();
        }
    }
}
