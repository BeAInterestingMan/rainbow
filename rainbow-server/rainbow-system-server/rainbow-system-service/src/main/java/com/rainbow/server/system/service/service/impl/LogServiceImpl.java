package com.rainbow.server.system.service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.common.core.entity.system.Log;
import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.server.system.service.annotation.RainbowLog;
import com.rainbow.server.system.service.mapper.LogMapper;
import com.rainbow.server.system.service.service.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 *  @Description 用户操作日志表
 *  @author liuhu
 *  @Date 2020-06-04 11:10:55
 */
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    private final ObjectMapper objectMapper;

    @Override
    public void saveLog(ProceedingJoinPoint joinPoint,String username,String ip) {
        Log log = new Log();
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
//        String ip = RainbowUtil.getHttpServletRequestIpAddress();
        // 类名称
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            log.setParams(String.valueOf(params));
        }
        RainbowLog annotation = method.getAnnotation(RainbowLog.class);
        String description = annotation.description();
        long end = System.currentTimeMillis();
        log.setOperation(description);
        log.setMethod(className + "." + methodName + "()");
        log.setUsername(username);
        log.setIp(ip);
//        log.setLocation(AddressUtil.getCityInfo(ip));
        log.setLocation("");
        log.setTime(end-start);
        log.setCreateTime(new Date());
        logMapper.insert(log);
    }

    /**
     * @Description 处理方法参数
     * @author liuhu
     * @createTime 2020-05-29 16:34:05
     * @param params
     * @param args
     * @param paramNames
     * @return java.lang.StringBuilder
     */
    @SuppressWarnings("all")
    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Map) {
                    Set set = ((Map) args[i]).keySet();
                    List<Object> list = new ArrayList<>();
                    List<Object> paramList = new ArrayList<>();
                    for (Object key : set) {
                        list.add(((Map) args[i]).get(key));
                        paramList.add(key);
                    }
                    return handleParams(params, list.toArray(), paramList);
                } else {
                    if (args[i] instanceof Serializable) {
                        Class<?> aClass = args[i].getClass();
                        try {
                            aClass.getDeclaredMethod("toString", new Class[]{null});
                            // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                        } catch (NoSuchMethodException e) {
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                        }
                    } else if (args[i] instanceof MultipartFile) {
                        MultipartFile file = (MultipartFile) args[i];
                        params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                    } else {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                    }
                }
            }
        } catch (Exception ignore) {
            params.append("参数解析失败");
        }
        return params;
    }
}
