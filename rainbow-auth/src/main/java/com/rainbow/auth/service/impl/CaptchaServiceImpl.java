package com.rainbow.auth.service.impl;

import com.rainbow.auth.properties.CaptchaProperties;
import com.rainbow.auth.service.CaptchaService;
import com.rainbow.common.core.constant.CaptchaConstant;
import com.rainbow.common.core.exception.RainbowException;
import com.rainbow.common.redis.service.RedisService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description captcha实现类
 *  @author liuhu
 *  @Date 2020/5/13 13:33
 */
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private final RedisService redisService;

    private final CaptchaProperties captchaProperties;


    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应头
        setHeader(response, captchaProperties.getType());
        // 得到key 然后把生成的验证码code 存入redis
        String captchaKey = request.getParameter(CaptchaConstant.CAPTCHA_KEY);
        Captcha captcha = createCaptcha(captchaProperties);
        // 验证码存入redis
        redisService.set(CaptchaConstant.CAPTCHA_KEY_PREFIX+captchaKey,StringUtils.lowerCase(captcha.text()),captchaProperties.getTime());
        // 输出图片流
        captcha.out(response.getOutputStream());
    }

    @Override
    public void checkCaptcha(String key,String code) {
        if (StringUtils.isBlank(code)) {
            throw new RainbowException("请输入验证码");
        }
        String value = (String)redisService.get(CaptchaConstant.CAPTCHA_KEY_PREFIX+key);
        if(StringUtils.isBlank(value)){
            throw new RainbowException("验证码已过期");
        }
        if(!StringUtils.equals(code,value)){
            throw new RainbowException("验证码不正确");
        }
    }


    private Captcha createCaptcha(CaptchaProperties captchaProperties) {
        Captcha captcha = null;
        // 类型
        if(StringUtils.equals(captchaProperties.getType(), CaptchaConstant.PNG)){
            captcha = new SpecCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLength());
        }else {
            captcha = new GifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLength());
        }
        // 多少为
        captcha.setCharType(captchaProperties.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, CaptchaConstant.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
