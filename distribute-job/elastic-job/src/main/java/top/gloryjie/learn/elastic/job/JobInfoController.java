package top.gloryjie.learn.elastic.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.infra.pojo.JobConfigurationPOJO;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobAPIFactory;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobConfigurationAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobOperateAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.ShardingOperateAPI;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Jie
 * @since 2020/8/19
 */
@Slf4j
@RestController
@RequestMapping("/job")
public class JobInfoController implements ApplicationContextAware {


    ApplicationContext context;

    JobConfigurationAPI jobConfigurationAPI;

    JobOperateAPI jobOperateAPI;

    ShardingOperateAPI shardingOperateAPI;

    private OneOffJobBootstrap jobBootstrap;

    @PostConstruct
    public void init(){
        String zk = "localhost:2181";
        String namespace = "elasticjob";
        jobConfigurationAPI = JobAPIFactory.createJobConfigurationAPI(zk,namespace, null);
        jobOperateAPI = JobAPIFactory.createJobOperateAPI(zk,namespace, null);
        shardingOperateAPI = JobAPIFactory.createShardingOperateAPI(zk,namespace, null);

    }

    @RequestMapping("")
    public JobConfigurationPOJO getJob(@RequestParam("name")String name){
        return jobConfigurationAPI.getJobConfiguration(name);
    }


    @PostMapping("/trigger")
    public String trigger(@RequestParam("name")String name, @RequestParam("sharding")int sharding){
        JobConfigurationPOJO jobConfiguration = jobConfigurationAPI.getJobConfiguration(name);
        jobConfiguration.setShardingTotalCount(sharding);

        jobConfigurationAPI.updateJobConfiguration(jobConfiguration);
        log.info("准触发任务：{}", name);
        jobOperateAPI.trigger(name);
        return "success";
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
