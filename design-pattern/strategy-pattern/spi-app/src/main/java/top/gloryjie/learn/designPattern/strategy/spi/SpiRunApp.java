package top.gloryjie.learn.designPattern.strategy.spi;

import java.util.ServiceLoader;

/**
 * @author jie
 * @since 2020/4/11
 */
public class SpiRunApp {

    public static void main(String[] args) {
        // 会自动找到具体的实现
        ServiceLoader<HelloSpi> helloSpis = ServiceLoader.load(HelloSpi.class);

        for (HelloSpi helloSpi : helloSpis) {
            helloSpi.sayHello();
        }
    }
}
