package top.gloryjie.learn.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 创建NameEvent的工厂，在Disruptor初始化循环队列（数组）时，填充元素时使用
 * @author Jie
 * @since 2020/8/16
 */
public class NameEventFactory implements EventFactory<NameEvent> {

    @Override
    public NameEvent newInstance() {
        return new NameEvent();
    }

}
