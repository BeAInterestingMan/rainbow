package com.rainbow.job.service.utils;

import com.rainbow.job.api.entity.Job;
import com.rainbow.job.service.configure.ScheduleJob;
import com.rainbow.job.service.constant.JobConstant;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
/**
 *  @Description 任务工具类
 *  @author liuhu
 *  @Date 2020/6/28 13:56
 */
@Slf4j
public class JobUtil {
    /**
     * @Description 创建定时任务
     * @author liuhu
     * @createTime 2020-06-28 13:57:07
     * @param scheduler
     * @param job
     * @return void
     */
    public  static void createScheduleJob(Scheduler scheduler, Job job) {
        try {
            // 构建任务信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class)
                    .withIdentity(getJobKey(job.getJobId()))
                    .build();
            // 构造corn表达式
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            // 构造触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(job.getJobId()))
                    .withSchedule(cronScheduleBuilder).build();
            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(JobConstant.JOB_PARAM_KEY, job);
            // 执行任务
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }catch (Exception e){
            e.printStackTrace();
            log.error("创建定时任务失败", e);
        }
    }

    /**
     * @Description 暂停定时任务
     * @author liuhu
     * @createTime 2020-06-28 13:52:59
     * @param scheduler
     * @param jobId
     * @return void
     */
    public static void parseJob(Scheduler scheduler,Long jobId) {
         log.info("定时任务被暂停，jobId:{}",jobId);
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("恢复定时任务失败", e);
        }
    }

    /**
     * @Description 执行定时任务
     * @author liuhu
     * @createTime 2020-06-28 13:52:45
     * @param scheduler
     * @param scheduleJob
     * @return void
     */
    public static void run(Scheduler scheduler, Job scheduleJob)  {
        // 存入参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(JobConstant.JOB_PARAM_KEY, scheduleJob);
        log.info("定时任务执行，jobId:{}",scheduleJob.getJobId());
        try {
            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
        }catch (SchedulerException e){
            log.error("执行定时任务失败", e);
        }
    }

    /**
     * @Description 删除定时任务
     * @author liuhu
     * @createTime 2020-06-28 13:57:33
     * @param scheduler
     * @param jobId
     * @return void
     */
    public static void delete(Scheduler scheduler, Long jobId) {
        log.info("删除定时任务，jobId:{}",jobId);
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败", e);
        }
    }

    /**
     * @Description 更新定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:47:41
     * @param scheduler
     * @param scheduleJob
     * @return void
     */
    public static void updateScheduleJob(Scheduler scheduler, Job scheduleJob) {
        try {
            // 暂停任务
            if (scheduleJob.getStatus().equals(JobConstant.PAUSE)) {
                parseJob(scheduler, scheduleJob.getJobId());
            }else {
                TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                        .cronSchedule(scheduleJob.getCronExpression())
                        .withMisfireHandlingInstructionDoNothing();
                CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getJobId());

                if (trigger == null) {
                    throw new SchedulerException("获取Cron表达式失败");
                } else {
                    // 按新的 cronExpression表达式重新构建 trigger
                    trigger = trigger.getTriggerBuilder()
                            .withIdentity(triggerKey)
                            .withSchedule(scheduleBuilder)
                            .build();
                    // 参数
                    trigger.getJobDataMap().put(JobConstant.JOB_PARAM_KEY, scheduleJob);
                }
                scheduler.rescheduleJob(triggerKey, trigger);
            }

        } catch (SchedulerException e) {
            log.error("更新定时任务失败", e);
        }
    }

    /**
     * @Description 生成jobKey
     * @author liuhu
     * @createTime 2020-06-24 17:49:58
     * @param jobId
     * @return org.quartz.JobKey
     */
    public static JobKey getJobKey(Long jobId){
       return  JobKey.jobKey(String.valueOf(jobId));
    }

    /**
     * @Description 生成TriggerKey
     * @author liuhu
     * @createTime 2020-06-24 17:49:58
     * @param jobId
     * @return org.quartz.JobKey
     */
    public static TriggerKey getTriggerKey(Long jobId){
        return  TriggerKey.triggerKey(String.valueOf(jobId));
    }


    /**
     * @Description 获取表达式触发器
     * @author liuhu
     * @createTime 2020-06-28 14:46:15
     * @param scheduler
     * @param jobId
     * @return org.quartz.CronTrigger
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("获取Cron表达式失败", e);
        }
        return null;
    }
}
