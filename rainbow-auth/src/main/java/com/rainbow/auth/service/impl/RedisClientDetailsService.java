package com.rainbow.auth.service.impl;

import com.rainbow.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
/**
 *  @Description 客户端配置核心类
 *  @author liuhu
 *  @Date 2020/5/11 18:38
 */
@Service
public class RedisClientDetailsService extends JdbcClientDetailsService {
    @Autowired
    @SuppressWarnings("all")
    private  RedisService redisService;

    public RedisClientDetailsService(DataSource dataSource, RedisService redisService) {
        super(dataSource);
        this.redisService = redisService;
    }

    /**
     * @Description 加载客户端信息
     * @author liuhu
     * @createTime
     * @param clientId 客户端ID
     * @return org.springframework.security.oauth2.provider.ClientDetails
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
