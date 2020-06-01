package com.rainbow.auth.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.auth.mapper.OauthClientDetailsMapper;
import com.rainbow.auth.service.OauthClientDetailsService;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.OauthClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *  @Description 客户端信息实现类
 *  @author liuhu
 *  @Date 2020/5/28 10:09
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final RedisClientDetailsService redisClientDetailsService;
    private final OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public IPage<OauthClientDetails> findOauthClientDetails(QueryRequest request, OauthClientDetails oauthClientDetails) {
        IPage<OauthClientDetails> oauthClientDetailsIPage = null;
        try {
            Page<OauthClientDetails> page = new Page<>(request.getPageNum(),request.getPageSize());
            oauthClientDetailsIPage = oauthClientDetailsMapper.selectOauthClientDetailsPage(page,oauthClientDetails);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public OauthClientDetails findById(String clientId) {
        return null;
    }

    @Override
    public void addOauthClientDetails(OauthClientDetails oauthClientDetails) {

    }

    @Override
    public void updateOauthClientDetails(OauthClientDetails oauthClientDetails) {

    }

    @Override
    public void deleteOauthClientDetails(String clientIds) {

    }
}

