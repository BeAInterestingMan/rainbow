package com.rainbow.common.security.handler;


import com.rainbow.common.core.utils.RainbowUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 处理403 没有权限异
 *  @author liuhu
 *  @Date 2020/5/12 17:05
 */
public class RainbowAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        RainbowUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, "没有权限访问该资源");
    }
}
