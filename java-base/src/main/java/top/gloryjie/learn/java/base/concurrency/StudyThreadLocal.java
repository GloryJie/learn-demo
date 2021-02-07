package top.gloryjie.learn.java.base.concurrency;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author Jie
 * @since 2020/11/8
 */
public class StudyThreadLocal {

    public static void main(String[] args) throws Exception{

        ThreadLocal<String> userNameHolder = ThreadLocal.withInitial(()-> "jojo");

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " get user from ThreadLocal: " + userNameHolder.get());
        }, "TestThread-1").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " get user from ThreadLocal: " + userNameHolder.get());
        }, "TestThread-2").start();


        TimeUnit.SECONDS.sleep(1);
    }
}
