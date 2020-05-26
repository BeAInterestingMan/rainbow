package com.rainbow.auth.controller;

import com.rainbow.auth.service.impl.CaptchaService;
import com.rainbow.bus.api.entity.RainbowMail;
import com.rainbow.bus.api.feign.RainbowMailFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 *  @Description 认证提供的接口
 *  @author liuhu
 *  @Date 2020/5/13 13:23
 */
@RestController
public class SecurityController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private RainbowMailFeign rainbowMailFeign;


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

    /**
     * @Description 认证服务器校验token
     * @author liuhu
     * @createTime 2020-05-19 18:54:54
     * @param principal
     * @return java.security.Principal
     */
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("test")
    public void send(){
        RainbowMail rainbowMail = new RainbowMail();
        rainbowMail.setToMailAddress("1649471814@qq.com");
        rainbowMail.setToMailAddress("1649471814@qq.com");
        rainbowMail.setSubject("11111");
        rainbowMail.setText("111111");
        rainbowMailFeign.send(rainbowMail);
    }
}
