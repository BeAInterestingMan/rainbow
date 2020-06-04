package com.rainbow.server.system.service.controller;


import com.rainbow.server.system.service.service.LogService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuhu
 * @Description 日志控制层
 * @Date 2020/6/3 13:41
 */
@RestController
@RequestMapping("log")
@RequiredArgsConstructor
@Api(tags = "日志处理接口")
public class LogController {

    private final LogService logService;


}
