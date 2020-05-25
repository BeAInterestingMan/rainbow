package com.rainbow.bus.service.handler;

import com.rainbow.bus.service.exception.BusException;
import com.rainbow.common.core.handler.BaseExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 *  @Description 消息服务异常处理
 *  @author liuhu
 *  @Date 2020/5/25 15:31
 */
@RestControllerAdvice
public class BusExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(BusException.class)
    public ResponseEntity handlerBusException(BusException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
