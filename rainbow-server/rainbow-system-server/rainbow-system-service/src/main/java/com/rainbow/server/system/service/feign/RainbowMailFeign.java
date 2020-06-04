package com.rainbow.server.system.service.feign;

import com.rainbow.server.system.api.entity.dto.RainbowMail;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *  @Description 邮件服务feign
 *  @author liuhu
 *  @Date 2020/5/25 16:39
 */
@FeignClient("rainbow-bus")
public interface RainbowMailFeign {

    /**
     * @Description 发送邮件
     * @author liuhu
     * @createTime 2020-05-25 15:50:39
     * @param rainbowMail
     * @return org.springframework.http.ResponseEntity
     */
    @ApiOperation("发送邮件")
    @PostMapping("email")
    ResponseEntity send(@RequestBody RainbowMail rainbowMail);

}
