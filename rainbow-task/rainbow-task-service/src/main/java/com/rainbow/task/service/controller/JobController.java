package com.rainbow.task.service.controller;


import com.rainbow.task.service.service.JobService;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
*  @Description oauthClient Controller
*  @author liuhu
*  @Date 2020-6-23 16:33:16
*/
@RestController
@RequestMapping("job")
@RequiredArgsConstructor
@Api(tags = "oauthClient")
public class JobController {

    private final JobService jobService;

}
