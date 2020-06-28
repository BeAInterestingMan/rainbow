package com.rainbow.job.service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.job.api.entity.JobLog;
import org.springframework.stereotype.Repository;

/**
*  @Description jobLog 持久层接口
*  @author liuhu
*  @Date 2020-6-28 15:02:16
*/
@Repository
public interface JobLogMapper extends BaseMapper<JobLog> {

}
