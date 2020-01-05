package top.gloryjie.learn.cache.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author jie
 * @since 2020/1/4
 */
@EnableCaching
@SpringBootApplication
public class SpringCacheApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheApp.class, args);
    }
}
