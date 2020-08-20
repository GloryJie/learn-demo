package top.gloryjie.learn.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * Event的生产者
 * @author Jie
 * @since 2020/8/16
 */
public class NameProducer {

    private final RingBuffer<NameEvent> ringBuffer;

    public NameProducer(RingBuffer<NameEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void publish(String name){
        ProducerInternal producerInternal = new ProducerInternal();
        ringBuffer.publishEvent(producerInternal, name);
    }


    /**
     * 使用Disruptor新的api进行消息转换, 不需要自己手动申请sequence
     */
    static class ProducerInternal implements EventTranslatorOneArg<NameEvent, String> {

        /**
         *
         * @param event Disruptor传递进来的event，对其填充属性即可
         * @param sequence 成功申请的序列
         * @param arg0 传递进来的参数，就是name
         */
        @Override
        public void translateTo(NameEvent event, long sequence, String arg0) {
            event.setMsg(arg0);
            event.setSequence(sequence);
        }
    }
}
