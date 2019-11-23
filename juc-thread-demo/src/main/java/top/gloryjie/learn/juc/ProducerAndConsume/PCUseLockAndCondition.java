package top.gloryjie.learn.juc.ProducerAndConsume;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jie
 * @since 2019/5/15
 */
public class PCUseLockAndCondition {

    public static void main(String[] args) {
        AssembleLine assembleLine = new AssembleLine();

        Producer producer = new Producer(assembleLine);
        Consumer consumer = new Consumer(assembleLine);

        producer.start();
        consumer.start();
    }

}


/**
 * 流水线
 */
class AssembleLine {

    private static final int CAPACITY = 10;
    private  final Queue<Integer> queue = new LinkedList<>();
    private final Random theRandom = new Random();

    private final Lock lock = new ReentrantLock();
    // 队列不满的条件
    private final Condition notFull = lock.newCondition();
    // 队列非空的条件
    private final Condition notEmpty = lock.newCondition();

    public void put() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY){
                System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting");
                // 队列已满条件, 等待消费
                notFull.await();
            }
            int number = theRandom.nextInt();
            boolean isAdded = queue.offer(number);
            if (isAdded){
                System.out.printf("%s added %d into queue %n", Thread.currentThread().getName(), number);

                // signal consumer thread that, buffer has element now
                System.out.println(Thread.currentThread().getName() + " : Signalling that buffer is no more empty now");
                // 添加成功, 队列非空, 通知消费
                notEmpty.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }

    public void get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0){
                System.out.println(Thread.currentThread().getName() + " : Buffer is empty, waiting");
                // 队列为空
                notEmpty.await();
            }
            Integer value = queue.poll();
            if (value != null) {
                System.out.printf("%s consumed %d from queue %n", Thread
                        .currentThread().getName(), value);

                // signal producer thread that, buffer may be empty now
                System.out.println(Thread.currentThread().getName()
                        + " : Signalling that buffer may be empty now");
                // 消费成功, 队列不满, 通知生产
                notFull.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 生产者
 */
class Producer extends Thread {

    AssembleLine line;

    public Producer(AssembleLine line) {
        super("PRODUCER");
        this.line = line;
    }

    @Override
    public void run() {
        try {
            line.put();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    AssembleLine line;

    public Consumer(AssembleLine line) {
        super("CONSUMER");
        this.line = line;
    }

    @Override
    public void run() {
        try {
            line.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
