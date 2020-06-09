package com.rainbow.generator.handler;

import com.rainbow.generator.exception.GeneratorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 代码生成模块异常处理
 *  @author liuhu
 *  @Date 2020/6/9 11:55
 */
@RestControllerAdvice
public class GeneratorExceptionHandler {
    @ExceptionHandler(GeneratorException.class)
    public ResponseEntity handlerAuthExceptionHandler(GeneratorException e){
        return ResponseEntity.ok(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerAuthExceptionHandler(Exception e){
        e.printStackTrace();
        return ResponseEntity.ok("系统内部错误");
    }
}
