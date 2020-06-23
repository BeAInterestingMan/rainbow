package com.rainbow.task.service.controller;


import com.rainbow.task.service.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
*  @Description job Controller
*  @author liuhu
*  @Date 2020-6-23 17:08:09
*/
@RestController
@RequestMapping("loginLog")
@RequiredArgsConstructor
@Api(tags = "job")
public class LoginLogController {

    private final LoginLogService loginLogService;

}
