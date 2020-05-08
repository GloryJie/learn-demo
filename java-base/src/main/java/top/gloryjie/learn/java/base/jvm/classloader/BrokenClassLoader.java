package top.gloryjie.learn.java.base.jvm.classloader;


import java.net.URL;
import java.net.URLClassLoader;

/**
 * 打破双亲委派模型
 *
 * @author jie
 * @since 2020/3/2
 */
public class BrokenClassLoader extends URLClassLoader {

    public BrokenClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                try {
                    // 此处就打破双亲委派
                    c = findClass(name);
                } catch (ClassNotFoundException e) {
                    // ignore
                }
                if (c == null) {
                    if (getParent() != null) {
                        // 自身无法加载,则直接交给父类或SystemClassLoader(AppClassLoader)
                        c = getParent().loadClass(name);
                    } else {
                        c = getSystemClassLoader().loadClass(name);
                    }
                }
            }
            if (c == null) {
                throw new ClassNotFoundException(name);
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    /*****************继承ClassLoader的实现,自己读取文件数据**********************/
//    private Path dirPath;


    //    public BrokenClassLoader(String dir, ClassLoader parent) {
//        super(parent);
//        dirPath = Paths.get(dir);
//    }

//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        byte[] bytes = this.readClassFileByte(name);
//        if (bytes.length == 0){
//            throw new ClassNotFoundException(name);
//        }
//        // 将读读取的文件数据转换成Class实例
//        return this.defineClass(name, bytes,0, bytes.length);
//    }

//    /**
//     * 读取class文件数据
//     * @param name
//     * @return
//     * @throws ClassNotFoundException
//     */
//    private byte[] readClassFileByte(String name) throws ClassNotFoundException {
//        String classPath = name.replace('.', '/').concat(".class");
//        Path fullPath = dirPath.resolve(Paths.get(classPath));
//        if (!fullPath.toFile().exists()){
//            throw new ClassNotFoundException(name);
//        }
//
//        try (ByteArrayOutputStream os = new ByteArrayOutputStream();){
//            Files.copy(fullPath, os);
//            return os.toByteArray();
//        } catch (IOException e) {
//            throw new ClassNotFoundException(name);
//        }
//
//    }
}
