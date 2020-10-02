package top.gloryjie.learn.java.base.reflect;

import lombok.SneakyThrows;

import javax.management.relation.Relation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 研究反射原理
 * @author Jie
 * @since 2020/9/26
 */
public class ReflectDemo {

    private String name;

    private void sayHello(){
//        System.out.println("【ReflectDemo】say hello method been invoked, 【name=" + name + "】");
    }

    @SneakyThrows
    public static void main(String[] args) {

        Class<ReflectDemo> reflectDemoClass = ReflectDemo.class;
        // 获取构造方法、属性、方法等元数据信息
        Constructor<?> constructor = reflectDemoClass.getConstructor();
        Field nameField = reflectDemoClass.getDeclaredField("name");
        Method sayHelloMethod = reflectDemoClass.getDeclaredMethod("sayHello");
        Method sayHelloMethod2 = reflectDemoClass.getDeclaredMethod("sayHello");

        nameField.setAccessible(true);
        sayHelloMethod.setAccessible(true);

        // 通过元数据（元对象）信息来进行动态操作

        // 通过构造函数来创建实例
        ReflectDemo reflectDemo = (ReflectDemo) constructor.newInstance();

        // TODO 2020/9/26 如何赋值和调用的
        nameField.set(reflectDemo, "jojo");

        for (int i = 0; i < 30; i++) {
            long start = System.nanoTime();
            sayHelloMethod.invoke(reflectDemo);
            System.out.println(System.nanoTime() - start);
        }

        TimeUnit.MINUTES.sleep(30);

        System.out.println("【ReflectDemo end】");
    }


}
