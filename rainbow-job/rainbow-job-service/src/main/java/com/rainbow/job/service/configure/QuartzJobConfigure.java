package com.rainbow.job.service.configure;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.rainbow.job.service.constant.JobConstant;
import lombok.RequiredArgsConstructor;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import sun.security.util.SecurityConstants;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  @Description quartZ配置
 *  @author liuhu
 *  @Date 2020/6/28 14:55
 */
@RequiredArgsConstructor
@Configuration
public class  QuartzJobConfigure {

    private final DynamicRoutingDataSource dynamicRoutingDataSource;
    /**
     * @Description 线程池
     * @author liuhu
     * @createTime 2020-06-28 16:20:23
     * @param
     * @return org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
     */
    @Bean
    public ThreadPoolTaskExecutor scheduleJobExecutorService() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("rainbow-job-thread");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * SchedulerFactoryBean这个类的真正作用提供了对org.quartz.Scheduler的创建与配置，并且会管理它的生命周期与Spring同步。
     * org.quartz.Scheduler: 调度器。所有的调度都是由它控制。
     * 如果不配置  注入不了scheduler
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 设置数据源
        DataSource job = dynamicRoutingDataSource.getDataSource(JobConstant.DataSourceConstant.JOB);
        factory.setDataSource(job);
        Properties prop = new Properties();
        // 任务调度实例名称，集群时多个实例名称保持一致
        prop.put(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, "cloudScheduler");
        // 任务调度实例ID，指定为AUTO时，将自动生成ID
        prop.put(StdSchedulerFactory.PROP_SCHED_INSTANCE_ID, StdSchedulerFactory.AUTO_GENERATE_INSTANCE_ID);
        // quartz提供的简单线程池，适用于绝大部分场景
        prop.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, SimpleThreadPool.class);
        // 并发执行任务的线程数，取决于服务器系统资源
        prop.put("org.quartz.threadPool.threadCount", "20");
        // 可以是Thread.MIN_PRIORITY（1）和Thread.MAX_PRIORITY（10）之间的任何int值 。
        // 默认值为Thread.NORM_PRIORITY（5）
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // 指定任务存储策略，这里使用关系型数据库
        prop.put(StdSchedulerFactory.PROP_JOB_STORE_CLASS, JobStoreTX.class);
        // 是否开启集群
        prop.put("org.quartz.jobStore.isClustered", "true");
        // 集群中任务调度实例失效的检查时间间隔，单位为毫秒
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        // 数据表前缀
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        factory.setQuartzProperties(prop);
        factory.setSchedulerName(JobConstant.SCHEDULER_NAME);
        // 延时1分钟启动
//        factory.setStartupDelay(1);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 启动时更新己存在的 Job
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为 true
        factory.setAutoStartup(true);
        return factory;
    }
}
