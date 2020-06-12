package com.rainbow.server.system.service.configure;


import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.rainbow.common.core.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 *  @Description 自定义 p6spy sql输出格式
 *  @author liuhu
 *  @Date 2020/6/12 16:35
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? "==>    "+DateUtil.formatTime2String(LocalDateTime.now(),DateUtil.FULL_TIME_SPLIT_PATTERN)
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF
                + "                                                                                                     "
                +"<==    "+sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
    }
    
}
