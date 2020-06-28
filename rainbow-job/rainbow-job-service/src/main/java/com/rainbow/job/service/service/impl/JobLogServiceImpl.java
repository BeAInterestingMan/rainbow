package com.rainbow.job.service.service.impl;



import com.rainbow.job.api.entity.JobLog;
import com.rainbow.job.service.mapper.JobLogMapper;
import com.rainbow.job.service.service.JobLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
*  @Description jobLog 业务层实现类
*  @author liuhu
*  @Date 2020-6-28 15:02:16
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class JobLogServiceImpl  implements JobLogService {

    private final JobLogMapper jobLogMapper;

    @Override
    public void saveJobLog(JobLog jobLog) {
       jobLogMapper.insert(jobLog);
    }
}
