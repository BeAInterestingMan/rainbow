package com.rainbow.server.system.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.common.core.constant.RainbowConstant;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.Menu;
import com.rainbow.common.core.entity.system.SystemUser;
import com.rainbow.common.core.entity.system.UserDataPermission;
import com.rainbow.common.core.entity.system.UserRole;
import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.server.system.service.exception.SystemException;
import com.rainbow.server.system.service.mapper.MenuMapper;
import com.rainbow.server.system.service.mapper.UserDataPermissionMapper;
import com.rainbow.server.system.service.mapper.UserMapper;
import com.rainbow.server.system.service.mapper.UserRoleMapper;
import com.rainbow.server.system.service.service.IUserDataPermissionService;
import com.rainbow.server.system.service.service.IUserRoleService;
import com.rainbow.server.system.service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuhu
 * @Description 用户业务层实现类
 * @Date 2020/5/15 10:23
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    private final MenuMapper menuMapper;

    private final UserRoleMapper userRoleMapper;

    private final IUserRoleService userRoleService;

    private final IUserDataPermissionService dataPermissionService;

    private final PasswordEncoder passwordEncoder;

    private final UserDataPermissionMapper userDataPermissionMapper;

    @Override
    public SystemUser selectUserByUsername(String username) {
        try {
            SystemUser user = userMapper.findByName(username);
            List<UserDataPermission> permissions = userMapper.findUserDataPermissions(user.getUserId());
            String deptIds = permissions.stream().map(p -> String.valueOf(p.getDeptId())).collect(Collectors.joining(StringPool.COMMA));
            user.setDeptIds(deptIds);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
    }

    @Override
    public String findUserPermissions(String username) {
        String userPermission = "";
        try {
            List<Menu> userPermissions = menuMapper.findUserPermissions(username);
            userPermission = userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(StringPool.COMMA));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
        return userPermission;
    }

    @Override
    public IPage<SystemUser> userPage(QueryRequest queryRequest, SystemUser user) {
        IPage<SystemUser> iPage = null;
        try {
            Page<SystemUser> page = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
            iPage = userMapper.userPage(page, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
        return iPage;
    }

    @Override
    public SystemUser addUser(SystemUser user) {
        try {
            user.setPassword(passwordEncoder.encode(RainbowConstant.DEFAULT_PASSWORD));
            user.setAvatar(passwordEncoder.encode(RainbowConstant.DEFAULT_AVATAR));
            userMapper.insert(user);
            // 保存角色对应关系
            if (StringUtils.isNotBlank(user.getRoleIds())) {
                String[] roleIds = user.getRoleIds().split(StringPool.COMMA);
                saveUserRole(user.getUserId(),roleIds);
            }
            // 增加部门
            if (StringUtils.isNotBlank(user.getDeptIds())) {
                String[] deptIds = user.getDeptIds().split(StringPool.COMMA);
                saveDept(user.getUserId(),deptIds);
            }
            // 保存菜单对应关系
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("新增用户是不是");
        }
        return user;
    }

    @Override
    public void deleteUser(String ids) {
        try {
            // 删除用户
            String[] idList = ids.split(StringPool.COMMA);
            Long userId = RainbowUtil.getCurrentUser().getUserId();
            if(Arrays.asList(idList).contains(String.valueOf(userId))){
                throw new SystemException("删除的用户包含本身，不允许删除");
            }
            userMapper.deleteBatchIds(Arrays.asList(idList));
            // 删除用户角色关系表
            userRoleService.deleteUserRolesByUserId(idList);
            // 删除部门用户中间表
            dataPermissionService.deleteByUserIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
    }

    @Override
    public void resetPassword(Long id, String newPassword, String oldPassword) {
        try {
            if(!StringUtils.equals(newPassword,oldPassword)){
                throw new SystemException("新密码不能与旧密码一致");
            }else{
                SystemUser systemUser = userMapper.selectById(id);
                systemUser.setPassword(passwordEncoder.encode(newPassword));
                userMapper.updateById(systemUser);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("更改用户密码失败");
        }
    }

    @Override
    public SystemUser getUser(long id) {
        SystemUser systemUser = null;
        try {
             systemUser = userMapper.selectById(id);
             // TODO 增加角色和 部门等信息
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("通过ID得到用户失败");
        }
        return systemUser;
    }

    /**
     * @Description 用户部门中间表
     * @author liuhu
     * @createTime 2020-05-26 17:21:23
     * @param userId
     * @param deptIds
     * @return void
     */
    private void saveDept(Long userId, String[] deptIds) {
        Arrays.asList(deptIds).forEach(deptId->{
            UserDataPermission   dataPermission = new UserDataPermission();
            dataPermission.setDeptId(Long.valueOf(deptId));
            dataPermission.setUserId(userId);
            userDataPermissionMapper.insert(dataPermission);
        });
    }

    /**
     * @Description 保存中间表
     * @author liuhu
     * @createTime 2020-05-26 17:14:30
     * @param userId
     * @param roleIds
     * @return void
     */
    public void saveUserRole(Long userId, String[] roleIds) {
        Arrays.asList(roleIds).forEach(roleId->{
            UserRole userRole = new UserRole();
            userRole.setRoleId(Long.valueOf(roleId));
            userRole.setUserId(userId);
            userRoleMapper.insert(userRole);
        });
    }
}
