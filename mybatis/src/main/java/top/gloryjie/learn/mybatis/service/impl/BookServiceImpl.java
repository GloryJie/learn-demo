package top.gloryjie.learn.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.gloryjie.learn.mybatis.dao.BookDao;
import top.gloryjie.learn.mybatis.model.Book;
import top.gloryjie.learn.mybatis.service.BookService;

import java.util.List;

/**
 * @author jie
 * @since 2019/9/1
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> selectAuthorAllBook(String name, int startPage, int pageSize) {
        PageHelper.startPage(startPage, pageSize);
        // 实际返回的是Page
        List<Book> bookList = bookDao.selectByAuthor(name);
        return bookList;
    }
}
