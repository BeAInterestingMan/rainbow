package com.rainbow.auth.controller;

import com.rainbow.auth.service.impl.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 认证提供的接口
 *  @author liuhu
 *  @Date 2020/5/13 13:23
 */
@RestController
public class SecurityController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * @Description 生成验证码
     * @author liuhu
     * @createTime 2020-05-13 14:41:41
     * @param request
     * @param response
     * @return void
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
          captchaService.create(request,response);
    }
}
