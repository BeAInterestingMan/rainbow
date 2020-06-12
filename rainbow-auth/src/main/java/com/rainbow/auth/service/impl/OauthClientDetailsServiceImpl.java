package com.rainbow.auth.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.auth.exception.AuthException;
import com.rainbow.auth.mapper.OauthClientDetailsMapper;
import com.rainbow.auth.service.OauthClientDetailsService;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.OauthClientDetails;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;


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
              e.printStackTrace();
              throw new AuthException("查询客户端信息分页失败");
        }
        return oauthClientDetailsIPage;
    }

    @Override
    public OauthClientDetails findById(String clientId) {
        OauthClientDetails oauthClientDetails = null;
        try {
            // 先删除 mysql 在删除缓存
             oauthClientDetails = oauthClientDetailsMapper.selectById(clientId);
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthException("查询客户端信息失败");
        }
        return oauthClientDetails;
    }

    @Override
    public OauthClientDetails addOauthClientDetails(OauthClientDetails oauthClientDetails) {
        try {
            String secret = passwordEncoder.encode(oauthClientDetails.getOriginSecret());
            oauthClientDetails.setClientSecret(secret);
            oauthClientDetailsMapper.insert(oauthClientDetails);
        }catch (Exception e){
          e.printStackTrace();
          throw new AuthException("新增客户端信息失败");
        }
        return oauthClientDetails;
    }

    @Override
    public void updateOauthClientDetails(OauthClientDetails oauthClientDetails) {
        try {
            if(null !=oauthClientDetails.getClientId()){
                throw new AuthException("更新客户端信息失败");
            }
            // 先更新 mysql 在删除缓存
            String encode = passwordEncoder.encode(oauthClientDetails.getOriginSecret());
            oauthClientDetails.setClientSecret(encode);
            redisClientDetailsService.deleteClientCache(oauthClientDetails.getClientId());
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthException("更新客户端信息失败");
        }
    }

    @Override
    public void deleteOauthClientDetails(String clientIds) {
         try {
            // 先删除 mysql 在删除缓存
             oauthClientDetailsMapper.deleteById(clientIds);
             redisClientDetailsService.deleteClientCache(clientIds);
         }catch (Exception e){
              e.printStackTrace();
              throw new AuthException("删除客户端信息失败");
         }
    }
}

