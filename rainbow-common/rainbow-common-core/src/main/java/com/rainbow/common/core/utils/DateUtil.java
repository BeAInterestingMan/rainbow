package com.rainbow.common.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  @Description 时间工具类
 *  @author liuhu
 *  @Date 2020/5/26 13:23
 */
public class DateUtil {
    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * @Description 时间对象转string
     * @author liuhu
     * @createTime 2020-05-26 13:30:30
     * @param dateTime
     * @param format
     * @return java.lang.String
     */
    public static String formatTime2String (LocalDateTime dateTime,String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return  dateTimeFormatter.format(dateTime);
    }
}

