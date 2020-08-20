package top.gloryjie.learn.disruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

/**
 * 集群消费使用WorkHandler
 * @author Jie
 * @since 2020/8/16
 */
public class NameWorkHandler implements WorkHandler<NameEvent> {

    @Override
    public void onEvent(NameEvent event) throws Exception {
        System.out.println("Event: name=" + event.getMsg() + ", sequence=" + event.getSequence() + ", currentThread=" + Thread.currentThread().getName() + ", event=" + event);
        if ("jojo0".equalsIgnoreCase(event.getMsg())) {
            TimeUnit.SECONDS.sleep(10);
            return;
        }
        TimeUnit.SECONDS.sleep(3);
    }

}
