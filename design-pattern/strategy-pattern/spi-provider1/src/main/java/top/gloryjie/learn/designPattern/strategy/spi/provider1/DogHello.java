package top.gloryjie.learn.designPattern.strategy.spi.provider1;

import top.gloryjie.learn.designPattern.strategy.spi.HelloSpi;

/**
 * @author jie
 * @since 2020/4/11
 */
public class DogHello implements HelloSpi {

    @Override
    public void sayHello() {
        System.out.println("dog say hello");
    }

}
