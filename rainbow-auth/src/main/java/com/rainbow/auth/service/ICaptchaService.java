package com.rainbow.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description captcha 接口
 *  @author liuhu
 *  @Date 2020/5/13 14:51
 */
public interface ICaptchaService {
    /**
     * @Description 生成验证码
     * @author liuhu
     * @createTime 2020-05-13 14:53:32
     * @param request
     * @param response
     * @return void
     */
     void create(HttpServletRequest request, HttpServletResponse response) throws IOException;

     /**
      * @Description  校验验证码
      * @author liuhu
      * @createTime 2020-05-13 14:56:05
      * @param key 验证码的key
      * @param code 验证码值
      * @return boolean
      */
     void checkCaptcha(String key,String code);
}
