package com.rainbow.job.service.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.job.api.entity.Job;
import org.quartz.Scheduler;

/**
*  @Description oauthClient 业务层接口
*  @author liuhu
*  @Date 2020-6-23 16:33:16
*/
public interface JobService {

    /**
     * @Description 查询分页
     * @author liuhu
     * @createTime 2020-06-24 17:46:16
     * @param queryRequest
     * @param job
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.job.api.entity.Job>
     */
//    IPage<Job> selectPage(QueryRequest queryRequest, Job job);

    /**
     * @Description 新增定时任务
     * @author liuhu
     * @createTime 2020-06-24 17:46:23
     * @param job
     * @param scheduler
     * @return com.rainbow.job.api.entity.Job
     */
    Job create(Job job, Scheduler scheduler);

    /**
     * @Description 暂停定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:01:19
     * @param jobIds
     * @return void
     */
    void parse(String jobIds);

    /**
     * @Description 启动定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:15:53
     * @param jobIds
     * @return void
     */
    void run(String jobIds);

    /**
     * @Description 删除定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:39:32
     * @param jobIds
     * @return void
     */
    void delete(String jobIds);

    /**
     * @Description 跟新定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:43:04
     * @param job
     * @return void
     */
    void updateJob(Job job);

    /**
     * @Description 分页查询
     * @author liuhu
     * @createTime 2020-06-29 10:19:45
     * @param queryRequest
     * @param job
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.job.api.entity.Job>
     */
    IPage<Job> selectPage(QueryRequest queryRequest, Job job);
}
