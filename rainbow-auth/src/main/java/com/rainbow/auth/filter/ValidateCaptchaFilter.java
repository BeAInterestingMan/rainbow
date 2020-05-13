package com.rainbow.auth.filter;

import com.rainbow.auth.service.ICaptchaService;
import com.rainbow.common.core.constant.CaptchaConstant;
import com.rainbow.common.core.constant.EndpointConstant;
import com.rainbow.common.core.constant.GrantTypeConstant;
import com.rainbow.common.core.constant.ParamsConstant;
import com.rainbow.common.core.entity.RainbowResponse;
import com.rainbow.common.core.utils.RainbowUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *  @Description 验证码校验 OncePerRequestFilter表示只执行一次
 *  @author liuhu
 *  @Date 2020/5/13 14:49
 */
@Slf4j
@Component
public class ValidateCaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private ICaptchaService captchaService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher(EndpointConstant.OAUTH_TOKEN, HttpMethod.POST.toString());
        // 如果改请求时请求token 登录的 则校验 执行 验证码
        if (matcher.matches(httpServletRequest)
                && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter(ParamsConstant.GRANT_TYPE), GrantTypeConstant.PASSWORD)) {
            try {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (Exception e) {
                RainbowResponse response = new RainbowResponse();
                RainbowUtil.makeFailureResponse(httpServletResponse, response.message(e.getMessage()));
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void validateCode(HttpServletRequest httpServletRequest)  {
        String key = httpServletRequest.getParameter(CaptchaConstant.CAPTCHA_KEY);
        String code = httpServletRequest.getParameter(CaptchaConstant.CAPTCHA_VALUE);
        captchaService.checkCaptcha(key, code);
    }
}
