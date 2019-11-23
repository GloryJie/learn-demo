package top.gloryjie.learn.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.gloryjie.learn.mybatis.dao.BookDao;
import top.gloryjie.learn.mybatis.model.Book;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 原始的mybatis应用
 * @author jie
 * @since 2019/8/25
 */
public class RawMybatisApp {

    public static void main(String[] args) throws IOException {
        // 文件转换成流
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");

        // 构造SQLSessionFactory, 内部会解析config, mapper等的配置文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);


        SqlSession sqlSession = sqlSessionFactory.openSession();

        BookDao bookDao = sqlSession.getMapper(BookDao.class);

        // 分页插件测试, startPage李村ThreadLocal存储生成的Page, 插件从ThreadLocal中获取分页参数
        PageHelper.startPage(1, 10);
        // 实际返回的是Page
        List<Book> bookList = bookDao.selectByAuthor("小王子");

        System.out.println(bookList);
    }
}
