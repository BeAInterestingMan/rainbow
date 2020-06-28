package com.rainbow.job.service.controller;


import com.rainbow.job.api.entity.Job;
import com.rainbow.job.service.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
*  @Description oauthClient Controller
*  @author liuhu
*  @Date 2020-6-23 16:33:16
*/
@RestController
@RequestMapping("job")
@RequiredArgsConstructor
@Api(tags = "定时任务接口")
public class JobController {

    private final  Scheduler scheduler;

    private final JobService jobService;

    /**
     * @Description 查询定时任务分页
     * @author liuhu
     * @createTime 2020-06-24 17:43:31
     * @param queryRequest
     * @param job
     * @return org.springframework.http.ResponseEntity
     */
//    @GetMapping
//    @ApiOperation("查询定时任务分页")
//    public ResponseEntity list(QueryRequest queryRequest,Job job){
//            return ResponseEntity.ok(RainbowUtil.buildTableData(jobService.selectPage(queryRequest,job)));
//    }

    /**
     * @Description 新增定时任务
     * @author liuhu
     * @createTime 2020-06-24 17:44:45
     * @param job
     * @return void
     */
    @PostMapping
    @ApiOperation("新增定时任务")
    public ResponseEntity create(@RequestBody Job job) {
        jobService.create(job,scheduler);
       return ResponseEntity.ok().build();
    }

    /**
     * @Description 暂停定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:14:13
     * @param jobIds
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("parse/{jobIds}")
    @ApiOperation("暂停定时任务")
    public ResponseEntity parse(@PathVariable("jobIds")String jobIds)  {
       jobService.parse(jobIds);
       return ResponseEntity.ok().build();
    }

    /**
     * @Description 启动定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:15:37
     * @param jobIds
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("启动定时任务")
    @GetMapping("run/{jobIds}")
    public ResponseEntity run(@PathVariable("jobIds")String jobIds)  {
        jobService.run(jobIds);
        return ResponseEntity.ok().build();
    }

    /**
     * @Description 删除任务
     * @author liuhu
     * @createTime 2020-06-28 14:39:02
     * @param jobIds
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("删除定时任务")
    @DeleteMapping("{jobIds}")
    public ResponseEntity delete(@PathVariable("jobIds")String jobIds){
        jobService.delete(jobIds);
        return ResponseEntity.ok().build();
    }

    /**
     * @Description 更新定时任务
     * @author liuhu
     * @createTime 2020-06-28 14:42:47
     * @param job
     * @return void
     */
    @PutMapping
    @ApiOperation("更新定时任务")
    public void updateJob(@Valid Job job) {
        this.jobService.updateJob(job);
    }
}
