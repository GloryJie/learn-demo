package top.gloryjie.learn.disruptor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jie
 * @since 2020/8/16
 */
@Setter
@Getter
public class NameEvent {

    private String msg;

    private Long sequence;

}
