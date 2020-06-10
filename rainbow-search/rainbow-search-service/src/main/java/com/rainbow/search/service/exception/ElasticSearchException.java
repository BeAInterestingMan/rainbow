package com.rainbow.search.service.exception;
/**
 *  @Description 文件上传微服务异常
 *  @author liuhu
 *  @Date 2020/5/26 15:27
 */
public class ElasticSearchException extends RuntimeException{
    public ElasticSearchException(String message){
         super(message);
    }
}
