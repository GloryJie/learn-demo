package top.gloryjie.learn.shardingjdbc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.gloryjie.learn.shardingjdbc.mode.User;

import java.util.stream.IntStream;

/**
 * @author jie
 * @since 2019/8/4
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    UserDao userDao;


    @Test
    public void insertTest(){
//        IntStream.range(0,100).forEach(i->{
//            User user = new User();
//            user.setAge(22);
//            user.setUserName("jie");
//
//            userDao.save(user);
//        });

        ConfigurationPropertyName name = ConfigurationPropertyName.of("spring.shardingsphere.datasource.sharding_ds-0");
    }

}