package top.gloryjie.learn.java.base.jvm.init;

/**
 * @author jie
 * @since 2020/2/24
 */
public class Parent {

    static {
        System.out.println("the parent class is initialized");
    }

    public static int y = 100;


    public static String name = new String("test");

}
