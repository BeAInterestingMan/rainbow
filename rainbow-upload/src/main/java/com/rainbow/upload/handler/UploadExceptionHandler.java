package com.rainbow.upload.handler;

import com.rainbow.common.core.handler.BaseExceptionHandler;
import com.rainbow.upload.exception.UploadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 上传模块异常处理
 *  @author liuhu
 *  @Date 2020/5/13 18:24
 */
@RestControllerAdvice
public class UploadExceptionHandler  extends BaseExceptionHandler {
    @ExceptionHandler(UploadException.class)
    public ResponseEntity handlerAuthExceptionHandler(UploadException e){
        return ResponseEntity.ok(e.getMessage());
    }
}
