package top.gloryjie.learn.java.base.jvm.init;

/**
 * 探究初始化子类时会先初始化父类
 *
 * @author jie
 * @since 2020/2/24
 */
public class ActiveLoadTest {


    public static void main(String[] args) {

        // x为child的静态变量
//        System.out.println(Child.x);

        // y为Parent的静态变量, 通过子类访问父类静态变量,不会初始化子类
//        System.out.println(Child.y);

        System.out.println(Child.z);
    }
}
