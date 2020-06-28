package com.rainbow.job.service.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.rainbow.job.api.entity.Job;
import com.rainbow.job.service.constant.JobConstant;
import com.rainbow.job.service.exception.JobException;
import com.rainbow.job.service.mapper.JobMapper;
import com.rainbow.job.service.service.JobService;
import com.rainbow.job.service.utils.JobUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
*  @Description oauthClient 业务层实现类
*  @author liuhu
*  @Date 2020-6-23 16:33:16
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class JobServiceImpl  implements JobService {

    private final JobMapper jobMapper;
    private final Scheduler scheduler;

//    @Override
//    public IPage<Job> selectPage(QueryRequest queryRequest, Job job) {
//        IPage<Job> jobIPage = null;
//        try {
//            Page<Job> page = new Page<>(queryRequest.getPageNum(),queryRequest.getPageSize());
//            jobIPage = jobMapper.selectJobPage(page,job);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new JobException("查询定时任务列表失败");
//        }
//        return jobIPage;
//    }

    @Override
    public Job create(Job job, Scheduler scheduler) {
        try {
            job.setCreateTime(new Date());
            job.setStatus(JobConstant.RUN);
            jobMapper.insert(job);
            JobUtil.createScheduleJob(scheduler,job);
        }catch (Exception e){
            e.printStackTrace();
            log.error("创建任务失败",e);
        }
        return null;
    }

    @Override
    public void parse(String jobIds) {
        try {
            if(StringUtils.isNotBlank(jobIds)){
                List<String> ids = Arrays.asList(jobIds.split(StringPool.COMMA));
                ids.forEach(id->{
                    JobUtil.parseJob(scheduler,Long.valueOf(id));
                });
            }
        }catch (Exception e){
             e.printStackTrace();
             log.error("暂停任务失败",e);
        }
    }

    @Override
    public void run(String jobIds) {
        try {
            if(StringUtils.isNotBlank(jobIds)){
                List<String> ids = Arrays.asList(jobIds.split(StringPool.COMMA));
                ids.forEach(id->{
                    JobUtil.run(scheduler,jobMapper.selectById(id));
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("启动任务失败",e);
        }
    }

    @Override
    public void delete(String jobIds) {
        try {
            if(StringUtils.isNotBlank(jobIds)){
                List<String> ids = Arrays.asList(jobIds.split(StringPool.COMMA));
                ids.forEach(id->{
                    JobUtil.delete(scheduler,Long.valueOf(id));
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("启动任务失败",e);
        }
    }

    @Override
    public void updateJob(Job job) {
        JobUtil.updateScheduleJob(scheduler, job);
       jobMapper.updateById(job);
    }
}
