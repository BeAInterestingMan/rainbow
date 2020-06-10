package com.rainbow.search.service.handler;

import com.rainbow.common.core.handler.BaseExceptionHandler;
import com.rainbow.search.service.exception.ElasticSearchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 异常处理
 *  @author liuhu
 *  @Date 2020/6/10 18:10
 */
@RestControllerAdvice
public class ElasticSearchExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(ElasticSearchException.class)
    public ResponseEntity elasticSearchExceptionHandler(ElasticSearchException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
