package com.rainbow.common.core.utils;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.common.core.entity.CurrentUser;
import com.rainbow.common.core.entity.RainbowAuthUser;
import com.rainbow.common.core.entity.TableData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * FEBS工具类
 *
 * @author MrBird
 */
@Slf4j
public class RainbowUtil {

    private static final String UNKNOW = "unknown";

    /**
     * 驼峰转下划线
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1) {
                result.append(arr[i]).append("_");
            } else {
                result.append(arr[i]);
            }
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     * 设置响应
     * @param response    HttpServletResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @throws IOException IOException
     */
    public static void makeResponse(HttpServletResponse response, String contentType,
                                    int status, Object value) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
    }

    /**
     * 设置成功响应
     *
     * @param response HttpServletResponse
     * @param value    响应内容
     * @throws IOException IOException
     */
    public static void makeSuccessResponse(HttpServletResponse response, Object value) throws IOException {
        makeResponse(response, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_OK, value);
    }

    /**
     * 设置失败响应
     *
     * @param response HttpServletResponse
     * @param value    响应内容
     * @throws IOException IOException
     */
    public static void makeFailureResponse(HttpServletResponse response, Object value) throws IOException {
        makeResponse(response, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, value);
    }

    /**
     * 设置JSON类型响应
     *
     * @param response HttpServletResponse
     * @param status   http状态码
     * @param value    响应内容
     * @throws IOException IOException
     */
    public static void makeJsonResponse(HttpServletResponse response, int status, Object value) throws IOException {
        makeResponse(response, MediaType.APPLICATION_JSON_VALUE, status, value);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }

    /**
     * @Description 获取到token
     * @author liuhu
     * @createTime 2020-05-14 14:58:06
     * @param
     * @return java.lang.String
     */
    public static String getCurrentTokenValue() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) getOauth2Authentication().getDetails();
        return details.getTokenValue();
    }

    private static OAuth2Authentication getOauth2Authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2Authentication) authentication;
    }

    /**
     * @Description 自定义启动日志打印
     * @author liuhu
     * @createTime 2020-05-26 13:22:03
     * @param environment
     * @return void
     */
    public static void printSystemUpBanner(Environment environment) {
        String banner = "-----------------------------------------\n" +
                "服务启动成功，时间：" + DateUtil.formatTime2String(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN) + "\n" +
                "服务名称：" + environment.getProperty("spring.application.name") + "\n" +
                "端口号：" + environment.getProperty("server.port") + "\n" +
                "-----------------------------------------";
        System.out.println(banner);
    }

   /**
    * @Description 获取在线用户信息
    * @author liuhu
    * @createTime 2020-05-26 17:45:18
    * @param
    * @return com.rainbow.common.core.entity.CurrentUser
    */
    public static CurrentUser getCurrentUser() {
        try {
            LinkedHashMap<String, Object> authenticationDetails = (LinkedHashMap<String, Object>) getOauth2Authentication().getUserAuthentication().getDetails();
            Object principal = authenticationDetails.get("principal");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(mapper.writeValueAsString(principal), CurrentUser.class);
        } catch (Exception e) {
            log.error("获取当前用户信息失败", e);
            return null;
        }
    }
    /**
     * @Description
     * @author liuhu
     * @createTime 2020-05-26 15:08:31
     * @param
     * @return com.rainbow.common.core.entity.TableData
     */
    public static TableData buildTableData(IPage<?> iPage){
        TableData tableData = new TableData();
        tableData.setRows(iPage.getRecords());
        tableData.setTotal(tableData.getTotal());
        return tableData;
    }

    /**
     * @Description 获得用户名
     * @author liuhu
     * @createTime 2020-05-29 17:16:51
     * @param
     * @return java.lang.String
     */
    public static String getCurrentUsername() {
        Object principal = getOauth2Authentication().getPrincipal();
        if (principal instanceof RainbowAuthUser) {
            return ((RainbowAuthUser) principal).getUsername();
        }
        return (String) getOauth2Authentication().getPrincipal();
    }


    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取请求IP
     *
     * @return String IP
     */
    public static String getHttpServletRequestIpAddress() {
        HttpServletRequest request = getHttpServletRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
