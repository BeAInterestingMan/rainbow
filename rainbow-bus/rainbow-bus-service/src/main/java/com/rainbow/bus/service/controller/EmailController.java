package com.rainbow.bus.service.controller;

import com.rainbow.bus.api.entity.RainbowMail;
import com.rainbow.bus.service.product.EmailProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  @Description 发送邮件
 *  @author liuhu
 *  @Date 2020/5/25 16:04
 */
@Api(tags = "邮件服务")
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailProducer producer;

    /**
     * @Description 发送邮件
     * @author liuhu
     * @createTime 2020-05-25 15:50:39
     * @param rainbowMail
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("发送邮件")
    @PostMapping
    public ResponseEntity send(@RequestBody RainbowMail rainbowMail){
        producer.send(rainbowMail);
        return ResponseEntity.ok().build();
    }
}
