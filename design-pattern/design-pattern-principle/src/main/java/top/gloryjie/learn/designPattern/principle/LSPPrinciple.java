package top.gloryjie.learn.designPattern.principle;

import java.util.HashMap;
import java.util.Map;

/**
 * 里氏替换原则，父类可换成子类
 * 结论
 * 1. 只要是同名方法的参数定义的类型不一致（不论父子类），都是重载
 * 2. 会根据参数定义的类型，优先精确寻找符合的方法，之后才会到其可替代的参数父类
 *
 * @author Jie
 * @since 2020/11/4
 */
public class LSPPrinciple {

    public static void main(String[] args) {
        Father father = new Father();

        // 实验1：父类输入参数窄于子类
        System.out.println("******实验一: 父类输入参数窄于子类******");
        Son son = new Son();
        HashMap hashMap = new HashMap();
        son.doSomething(hashMap);

        // 实验2：父类输入参数宽于子类
        System.out.println("******实验二: 父类输入参数宽于子类******");
        son.doSomething2(hashMap);

        // 实验3：
        System.out.println("******实验三：参数定义为宽泛类 ******");
        Map map = new HashMap();
        son.doSomething(map);
        son.doSomething2(map);
    }

}


class Father {
    public void doSomething(HashMap map) {
        System.out.println("father 被执行");
    }

    public void doSomething2(Map map) {
        System.out.println("father 被执行");
    }
}

class Son extends Father {


    // 此时方法是 重载overload 而不是 覆写overwrite
    public void doSomething(Map map) {
        System.out.println("Son 被执行");
    }

    // 此时方法也是 重载overload 而不是 覆写overwrite
    public void doSomething2(HashMap map) {
        System.out.println("Son 被执行");
    }
}

