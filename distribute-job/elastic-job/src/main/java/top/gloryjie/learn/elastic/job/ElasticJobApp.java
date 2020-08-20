package top.gloryjie.learn.elastic.job;

import org.apache.shardingsphere.elasticjob.lite.internal.schedule.JobRegistry;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobConfigurationAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jie
 * @since 2020/8/19
 */
@SpringBootApplication
public class ElasticJobApp {



    public static void main(String[] args) {
        SpringApplication.run(ElasticJobApp.class, args);
    }

}
