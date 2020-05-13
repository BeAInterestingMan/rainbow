package com.rainbow.common.core.exception;
/**
 *  @Description 系统自定义异常
 *  @author liuhu
 *  @Date 2020/5/12 16:33
 */
public class RainbowException extends RuntimeException{
    public RainbowException(String message){
        super(message);
    }
}
