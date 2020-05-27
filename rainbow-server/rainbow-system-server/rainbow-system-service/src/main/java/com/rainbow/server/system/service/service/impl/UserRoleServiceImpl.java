package com.rainbow.server.system.service.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainbow.common.core.entity.system.UserRole;
import com.rainbow.server.system.service.exception.SystemException;
import com.rainbow.server.system.service.mapper.UserRoleMapper;
import com.rainbow.server.system.service.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 *  @Description 用户角色中间表
 *  @author liuhu
 *  @Date 2020/5/26 18:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserRoleServiceImpl implements IUserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public void deleteUserRolesByRoleId(String[] roleIds) {

    }

    @Override
    public void deleteUserRolesByUserId(String[] userIds) {
      try {
          LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
          userRoleMapper.delete(queryWrapper.in(UserRole::getUserId,userIds));
      }catch (Exception e){
          e.printStackTrace();
          throw new SystemException("删除失败");
      }
    }

    @Override
    public List<String> findUserIdsByRoleId(String[] roleIds) {
        return null;
    }
}
