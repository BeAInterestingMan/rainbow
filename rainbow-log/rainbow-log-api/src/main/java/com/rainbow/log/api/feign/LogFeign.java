package com.rainbow.log.api.feign;

import com.rainbow.log.api.entity.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "rainbow-log")
public interface LogFeign {
    @PostMapping
    public void addLog(Log log);
}
