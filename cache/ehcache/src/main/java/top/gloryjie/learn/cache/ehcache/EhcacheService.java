package top.gloryjie.learn.cache.ehcache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author jie
 * @since 2019/12/25
 */
@Service
public class EhcacheService {

    @Cacheable(value = "name")
    public String getValue(String key) {
        return key + ":value";
    }

    @CacheEvict(value = "name")
    public boolean updateValue(String key, String value) {
        return true;
    }


}
