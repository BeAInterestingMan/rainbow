package com.rainbow.common.security.intercept;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.rainbow.common.core.constant.RainbowConstant;
import com.rainbow.common.core.entity.RainbowResponse;
import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.common.security.properties.RainbowCloudSecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @Description 禁止请求直接绕过网关
 *  @author liuhu
 *  @Date 2020/5/19 16:36
 */

public class RainbowProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求头判断是否匹配网关token
        String token = request.getHeader(RainbowConstant.GATEWAY_TOKEN_HEADER);
        if(StringUtils.isBlank(token)){
            RainbowUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, new RainbowResponse().message("请通过网关获取资源"));
            return  false;
        }else{
            String value = new String(Base64Utils.encode(RainbowConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            if(StringUtils.equals(token,value)){
                return true;
            }
        }
        return false;
    }
}
