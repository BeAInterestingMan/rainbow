package com.rainbow.auth.properties;

import com.wf.captcha.base.Captcha;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Data
@Configuration
@ConfigurationProperties(prefix = "rainbow.captcha")
@PropertySource("classpath:captcha.properties")
public class CaptchaProperties {
    /**图片宽度*/
    private  Integer width;

    /**验证码位数*/
    private Integer length;

    /**图片高度*/
    private Integer height;

    /**设置类型，纯数字、纯字母、字母数字混合*/
    private Integer charType= Captcha.TYPE_ONLY_NUMBER;

    /**验证码类型 jpg png*/
    private String type;

    /**验证码失效时间*/
    private Long time;

}
