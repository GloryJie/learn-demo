package top.gloryjie.learn.designPattern.strategy.spi.provider2;

import top.gloryjie.learn.designPattern.strategy.spi.HelloSpi;

/**
 * @author jie
 * @since 2020/4/11
 */
public class CatHello implements HelloSpi {

    @Override
    public void sayHello() {
        System.out.println("Cat say hello");
    }

}
