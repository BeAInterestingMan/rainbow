package com.rainbow.job.service.handler;

import com.rainbow.common.core.handler.BaseExceptionHandler;
import com.rainbow.job.service.exception.JobException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 *  @Description 异常处理
 *  @author liuhu
 *  @Date 2020/6/29 10:17
 */
@RestControllerAdvice
@Slf4j
public class JobExceptionHandler extends BaseExceptionHandler {
    /**
     * @Description 任务异常处理
     * @author liuhu
     * @createTime 2020-05-12 16:40:37
     * @param e
     * @return org.springframework.http.ResponseEntity
     */
    @ExceptionHandler(JobException.class)
    public ResponseEntity handlerException(JobException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
