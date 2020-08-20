package top.gloryjie.learn.elastic.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jie
 * @since 2020/8/19
 */
@Slf4j
@Component
public class JobDemo implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("【执行任务={}】, 【总分片数={}】【当前分片={}】", shardingContext.getJobName(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
    }

}
