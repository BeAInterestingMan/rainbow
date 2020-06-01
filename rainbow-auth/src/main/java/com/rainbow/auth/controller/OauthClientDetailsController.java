package com.rainbow.auth.controller;


import com.rainbow.auth.service.OauthClientDetailsService;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.OauthClientDetails;
import com.rainbow.common.core.utils.RainbowUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 *  @Description 客户端信息
 *  @author liuhu
 *  @Date 2020/5/28 10:12
 */
@Api(tags = "客户端信息接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("client")
public class OauthClientDetailsController {

    private final OauthClientDetailsService oauthClientDetailsService;


    @GetMapping
    @PreAuthorize("hasAuthority('client:view')")
    public ResponseEntity oauthClientDetailsList(QueryRequest request, OauthClientDetails oAuthClientDetails) {
        return ResponseEntity.ok( RainbowUtil.buildTableData(this.oauthClientDetailsService.findOauthClientDetails(request, oAuthClientDetails)));
    }


    @PostMapping
    @PreAuthorize("hasAuthority('client:add')")
    public void addOauthClientDetails(@Valid OauthClientDetails oAuthClientDetails)  {

    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('client:delete')")
    public void deleteOauthClientDetails(@NotBlank(message = "{required}") String clientIds) {

    }

    @PutMapping
    @PreAuthorize("hasAuthority('client:update')")
    public void updateOauthClientDetails(@Valid OauthClientDetails oAuthClientDetails)  {

    }
}
