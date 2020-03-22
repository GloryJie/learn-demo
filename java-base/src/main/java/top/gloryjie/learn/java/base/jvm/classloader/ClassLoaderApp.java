package top.gloryjie.learn.java.base.jvm.classloader;

import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * @author jie
 * @since 2020/3/2
 */
public class ClassLoaderApp {


    /**
     * 验证一个Class在一个加载器下是唯一的
     * 测试同一个加载器加载一个类两遍
     *
     * @throws Exception
     */
    @Test
    public void testSameLoaderLoadTwice() throws Exception {
        ClassLoader classLoader = ClassLoaderApp.class.getClassLoader();
        // 同一个加载器实例加载的class,都是相同的
        Class aClass = classLoader.loadClass("top.gloryjie.learn.java.base.jvm.classloader.Person");
        Class bClass = classLoader.loadClass("top.gloryjie.learn.java.base.jvm.classloader.Person");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);

    }


    /**
     * 验证不同加载器加载同一class是否会是同一class实例
     *
     * @throws Exception
     */
    @Test
    public void testBrokenLoader() throws Exception {
        String dirPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes" + File.separator;
        BrokenClassLoader brokenClassLoader = new BrokenClassLoader(new URL[]{new URL("file:" + dirPath)}, null);

        // brokenClassLoader 和APPClassLoader都可以加载此类
        Class aClass = brokenClassLoader.loadClass("top.gloryjie.learn.java.base.jvm.init.Parent");
        Class bClass = ClassLoaderApp.class.getClassLoader().loadClass("top.gloryjie.learn.java.base.jvm.init.Parent");

        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());

        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass.equals(bClass));
        System.out.println(aClass == bClass);

        // 实例化,来验证初始化是需要加载java.lang.String
        // 验证访问String问题
        aClass.getConstructor().newInstance();
    }


    /**
     * 验证相同加载器的不同实例加载同一class是否会是同一class实例
     *
     * @throws Exception
     */
    @Test
    public void testBrokenLoader2() throws Exception {
        String dirPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes" + File.separator;
        BrokenClassLoader aBrokenClassLoader = new BrokenClassLoader(new URL[]{new URL("file:" + dirPath)}, null);
        BrokenClassLoader bBrokenClassLoader = new BrokenClassLoader(new URL[]{new URL("file:" + dirPath)}, null);

        Class aClass = aBrokenClassLoader.loadClass("top.gloryjie.learn.java.base.jvm.init.Parent");
        Class bClass = bBrokenClassLoader.loadClass("top.gloryjie.learn.java.base.jvm.init.Parent");

        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());

        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass.equals(bClass));
        System.out.println(aClass == bClass);
    }
}
