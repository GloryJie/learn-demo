package top.gloryjie.learn.mybatis.service;

import top.gloryjie.learn.mybatis.model.Book;

import java.util.List;

/**
 * @author jie
 * @since 2019/9/1
 */
public interface BookService {

    List<Book> selectAuthorAllBook(String name, int startPage, int pageSize);

}
