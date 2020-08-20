package top.gloryjie.learn.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jie
 * @since 2020/8/16
 */
public class DisruptorApp {

    public static void main(String[] args) {
        // ringBuffer size，需要为2^n
//        int ringBufferSize = 4;
//
//        // 创建 disruptor，指定为多生产者模式, 并且是阻塞等待策略
//        Disruptor<NameEvent> disruptor = new Disruptor<>(new NameEventFactory(), ringBufferSize, new ThreadFactory() {
//            final AtomicInteger count = new AtomicInteger();
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "jojoThread-" + count.getAndIncrement());
//            }
//        }, ProducerType.MULTI, new BlockingWaitStrategy());
//
//        // 可配置多个消费者，集群消费使用WorkHandler，广播消费使用EventHandler
//        disruptor.handleEventsWithWorkerPool(new NameWorkHandler(), new NameWorkHandler())
//                // 这里需要注意，then的回调并不是执行Handler的线程，而是一个新的线程
//                .then((event, sequence, endOfBatch) -> {
//                    System.out.println("Event=" + event.getMsg() + ", run finished" + ", currentThread=" +
//                            Thread.currentThread().getName());
//                    // 清理动作
//                    event.setSequence(null);
//                    event.setMsg(null);
//                });
//
//        // 启动disruptor
//        RingBuffer<NameEvent> ringBuffer = disruptor.start();
//
//        // 生产（发送）消息NameEvent
//        NameProducer producer = new NameProducer(ringBuffer);
//        for (int i = 0; i < 10; i++) {
//            producer.publish("jojo" + i);
//        }
//        System.out.println("end");

        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

    }

    static class MyStack {

        Queue<Integer> queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.add(x);
            for(int i = 1; i < queue.size(); i++)
                queue.add(queue.remove());
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.size() == 0;
        }
    }
}
