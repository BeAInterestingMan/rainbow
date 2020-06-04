package com.rainbow.server.system.service.aspect;

import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.server.system.service.service.LogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 *  @Description aop增加操作日志
 *  @author liuhu
 *  @Date 2020/6/4 14:30
 */
@Configuration
@Aspect
@RequiredArgsConstructor
public class RainbowLogAspect {

    private final LogService logService;

    @Pointcut("@annotation(com.rainbow.server.system.service.annotation.RainbowLog)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint point){

        try {
            point.proceed();
            String username = RainbowUtil.getCurrentUsername();
            String ip = RainbowUtil.getHttpServletRequestIpAddress();
            logService.saveLog(point, username, ip);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
