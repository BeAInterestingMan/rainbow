package com.rainbow.log.api.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.common.core.utils.AddressUtil;
import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.log.api.annotation.RainbowLog;
import com.rainbow.log.api.entity.Log;
import com.rainbow.log.api.feign.LogFeign;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;
/**
 *  @Description aop日志处理
 *  @author liuhu
 *  @Date 2020/5/29 17:08
 */
@Configuration
@Aspect
@RequiredArgsConstructor
public class RainbowLogAspect {
    private final ObjectMapper objectMapper;

    /**
     * @Description 定义切点
     * @author liuhu
     * @createTime 2020-05-29 16:17:26
     * @param
     * @return void
     */
    @Pointcut("@annotation(com.rainbow.log.api.annotation.RainbowLog)")
    public void pointcut(){

    }

    /**
     * @Description 执行逻辑
     * @author liuhu
     * @createTime 2020-05-29 17:07:10
     * @param joinPoint
     * @return java.lang.Object
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object result=null;
        try {
            saveLog(joinPoint);
            result= joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    /**
     * @Description 保存日志
     * @author liuhu
     * @createTime 2020-05-29 17:07:27
     * @param joinPoint
     * @return void
     */
    private void saveLog(ProceedingJoinPoint joinPoint) {
        Log log = new Log();
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        String ip = RainbowUtil.getHttpServletRequestIpAddress();
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
        String username = RainbowUtil.getCurrentUsername();
        log.setUsername(username);
        log.setIp(ip);
        log.setLocation(AddressUtil.getCityInfo(ip));
        log.setTime(end-start);
        // todo 增加日志

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
