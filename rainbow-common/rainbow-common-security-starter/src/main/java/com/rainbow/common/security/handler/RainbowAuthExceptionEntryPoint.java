package com.rainbow.common.security.handler;

import com.rainbow.common.core.entity.RainbowResponse;
import com.rainbow.common.core.utils.RainbowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  @Description 处理认证 401 未认证异常
 *  @author liuhu
 *  @Date 2020/5/12 17:04
 */
@Slf4j
public class RainbowAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        RainbowUtil.makeJsonResponse(response, status, new RainbowResponse().message(message));
    }
}
