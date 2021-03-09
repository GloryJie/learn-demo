package top.gloryjie.distribute.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author jie
 * @since 2021/3/7
 */
@Slf4j
@RestController
public class RedissonLockDemo {


    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/lock/{key}")
    public String lock(@PathVariable String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            lock.lock();

            log.info("获得锁{}成功", key);

            TimeUnit.SECONDS.sleep(15);

        } catch (InterruptedException e) {
            log.error("", e);
        } finally {
            lock.unlock();
            log.info("释放锁{}成功", key);
        }
        return "success";
    }


}
