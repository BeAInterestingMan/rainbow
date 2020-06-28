package com.rainbow.job.service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.rainbow.job.api.entity.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
*  @Description oauthClient 持久层接口
*  @author liuhu
*  @Date 2020-6-23 16:33:16
*/
@Repository
public interface JobMapper extends BaseMapper<Job> {
    /**
     * @Description 查询任务列表
     * @author liuhu
     * @createTime 2020-06-24 17:09:38
     * @param page
     * @param job
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.job.api.entity.Job>
     */
    IPage<Job> selectJobPage(Page<Job> page, @Param("job") Job job);
}
