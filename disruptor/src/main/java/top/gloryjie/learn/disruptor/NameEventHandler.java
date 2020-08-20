package top.gloryjie.learn.disruptor;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

/**
 * 广播消费使用EventHandler
 * @author Jie
 * @since 2020/8/16
 */
public class NameEventHandler implements EventHandler<NameEvent> {
    @Override
    public void onEvent(NameEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event: name=" + event.getMsg() + ", sequence=" + event.getSequence() + ", currentThread=" + Thread.currentThread().getName() + ", event=" + event);
        TimeUnit.SECONDS.sleep(3);
    }
}
