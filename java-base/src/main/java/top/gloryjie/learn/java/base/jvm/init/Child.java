package top.gloryjie.learn.java.base.jvm.init;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jie
 * @since 2020/2/24
 */
public class Child extends Parent {

    static {
        System.out.println("the child class is initialized");
    }

    public static int x = 10;

    public static final int z = ThreadLocalRandom.current().nextInt();

}
