package top.gloryjie.learn.shardingjdbc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gloryjie.learn.shardingjdbc.mode.User;

/**
 * @author jie
 * @since 2019/8/4
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {

}
