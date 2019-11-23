package top.gloryjie.learn.shardingjdbc.mode;

import lombok.Data;

import javax.persistence.*;

/**
 * @author jie
 * @since 2019/8/4
 */
@Data
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private Integer age;

}
