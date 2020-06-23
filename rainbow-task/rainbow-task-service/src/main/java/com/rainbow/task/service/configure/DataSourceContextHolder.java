package com.rainbow.task.service.configure;

/**
 *  @Description 存储当前线程内的线程池信息
 *  @author liuhu
 *  @Date 2020/6/22 17:17
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String key){
        THREAD_LOCAL.set(key);
    }

    public static String get(){
       return THREAD_LOCAL.get();
    }
}
