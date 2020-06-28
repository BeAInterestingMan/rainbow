package com.rainbow.job.service.service;

import com.rainbow.job.api.entity.JobLog;

/**
*  @Description jobLog 业务层接口
*  @author liuhu
*  @Date 2020-6-28 15:02:16
*/
public interface JobLogService {
    /**
     * @Description 保存任务
     * @author liuhu
     * @createTime 2020-06-28 15:29:49
     * @param jobLog
     * @return void
     */
    void saveJobLog(JobLog jobLog);
}
