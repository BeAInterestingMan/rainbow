package com.rainbow.auth.exception;
/**
 *  @Description 认证微服务异常
 *  @author liuhu
 *  @Date 2020/5/26 15:32
 */
public class AuthException extends RuntimeException{
    public AuthException(String message){
        super(message);
    }
}
