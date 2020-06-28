package com.rainbow.job.service.configure;


import com.rainbow.job.service.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 *  @Description 执行定时任务
 *  @author liuhu
 *  @Date 2020/6/28 15:27
 */
@Slf4j
public class ScheduleRunnable implements Runnable {

    private final Object target;
    private final Method method;
    private final String params;

    ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtil.getBean(beanName);
        this.params = params;

      try {
          if (StringUtils.isNotBlank(params)) {
              this.method = target.getClass().getDeclaredMethod(methodName, String.class);
          } else {
              this.method = target.getClass().getDeclaredMethod(methodName);
          }
      }catch (Exception e){
          e.printStackTrace();
          throw new RuntimeException("定时任务解析对象失败");
      }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            log.error("执行定时任务失败", e);
        }
    }

}
