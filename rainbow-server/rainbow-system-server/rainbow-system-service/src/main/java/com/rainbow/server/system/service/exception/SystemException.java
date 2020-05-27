package com.rainbow.server.system.service.exception;
/**
 *  @Description 系统微服务异常
 *  @author liuhu
 *  @Date 2020/5/26 15:27
 */
public class SystemException extends RuntimeException{
    public SystemException(String message){
         super(message);
    }
}
