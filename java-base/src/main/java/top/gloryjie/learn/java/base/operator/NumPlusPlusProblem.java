package top.gloryjie.learn.java.base.operator;

/**
 * 探究 i = i++ 问题
 * 需要使用javap 查看字节码指令
 *
 * @author jie
 * @since 2020/3/1
 */
public class NumPlusPlusProblem {

    public static void main(String[] args) {
        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println(NumPlusPlusProblem.class.getClassLoader());
        int i = 10;
        i = i++;
        System.out.println(i); // 结果为10
    }
}
