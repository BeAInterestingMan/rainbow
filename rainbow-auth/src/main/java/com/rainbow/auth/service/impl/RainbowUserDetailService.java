package com.rainbow.auth.service.impl;

import com.rainbow.auth.entity.FebsAuthUser;
import com.rainbow.auth.service.IUserService;
import com.rainbow.common.core.entity.RainbowAuthUser;
import com.rainbow.common.core.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @Description security 用户信息校验类
 *  @author liuhu
 *  @Date 2020/5/12 18:32
 */
@Service
public class RainbowUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userService.selectUserByUsername(username);
        if(null != user){
            String permissions = userService.findUserPermissions(username);
            // 是否锁定
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, user.getStatus())) {
                notLocked = true;
            }
            // 权限集合
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
            if (StringUtils.isNotBlank(permissions)) {
                grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(permissions);
            }
            // 构建返回用户信息
            RainbowAuthUser authUser = new RainbowAuthUser(user.getUsername(), user.getPassword(), true, true, true, notLocked,
                    grantedAuthorities);
            // 属性拷贝
            BeanUtils.copyProperties(user, authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
