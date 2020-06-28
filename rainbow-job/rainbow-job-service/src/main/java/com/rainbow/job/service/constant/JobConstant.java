package com.rainbow.job.service.constant;
/**
 *  @Description 定时任务常量
 *  @author liuhu
 *  @Date 2020/6/24 10:41
 */
public interface JobConstant {
    String SCHEDULER_NAME = "rainbow_scheduler";
    String JOB_PARAM_KEY = "";
    String PAUSE = "1";
    String RUN = "0";
    String JOB_SUCCESS = "0";
    String JOB_FAIL = "1";
    interface DataSourceConstant {
       String JOB ="job";
    }
}
