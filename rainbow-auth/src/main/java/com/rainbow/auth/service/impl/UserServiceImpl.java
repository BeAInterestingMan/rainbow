package com.rainbow.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.rainbow.auth.exception.AuthException;
import com.rainbow.auth.mapper.MenuMapper;
import com.rainbow.auth.mapper.UserMapper;
import com.rainbow.auth.service.UserService;
import com.rainbow.common.core.entity.system.Menu;
import com.rainbow.common.core.entity.system.SystemUser;
import com.rainbow.common.core.entity.system.UserDataPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  @Description 用户业务层实现类
 *  @author liuhu
 *  @Date 2020/5/15 10:23
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public SystemUser selectUserByUsername(String username) {
        try {
            SystemUser user = userMapper.findByName(username);
            List<UserDataPermission> permissions = userMapper.findUserDataPermissions(user.getUserId());
            String deptIds = permissions.stream().map(p -> String.valueOf(p.getDeptId())).collect(Collectors.joining(StringPool.COMMA));
            user.setDeptIds(deptIds);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthException("查询用户失败");
        }
    }

    @Override
    public String findUserPermissions(String username) {
        try {
            List<Menu> userPermissions = menuMapper.findUserPermissions(username);
            return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(StringPool.COMMA));
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthException("查询用户失败");
        }
    }
}
