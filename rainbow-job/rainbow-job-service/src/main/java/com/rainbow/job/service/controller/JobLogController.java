package com.rainbow.job.service.controller;


import com.rainbow.job.service.service.JobLogService;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
*  @Description jobLog Controller
*  @author liuhu
*  @Date 2020-6-28 15:02:16
*/
@RestController
@RequestMapping("jobLog")
@RequiredArgsConstructor
@Api(tags = "jobLog")
public class JobLogController {

    private final JobLogService jobLogService;

}
