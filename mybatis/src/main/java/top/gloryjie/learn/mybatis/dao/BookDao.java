package top.gloryjie.learn.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import top.gloryjie.learn.mybatis.model.Book;

import java.util.List;

/**
 * @author jie
 * @since 2019/8/25
 */
public interface BookDao {

    int update(Book book);

    int insert(Book book);

    int delete(int id);

    Book selectById(int id);

    List<Book> selectByAuthor(String author);

    Book selectByNameAndAuthor(@Param("name") String name,@Param("author") String author);

}
