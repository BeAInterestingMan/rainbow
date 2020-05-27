package com.rainbow.server.system.service.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.system.Role;
import com.rainbow.common.core.entity.system.RoleMenu;
import com.rainbow.common.core.entity.system.UserRole;
import com.rainbow.server.system.service.exception.SystemException;
import com.rainbow.server.system.service.mapper.RoleMapper;
import com.rainbow.server.system.service.mapper.RoleMenuMapper;
import com.rainbow.server.system.service.mapper.UserRoleMapper;
import com.rainbow.server.system.service.service.IRoleMenuService;
import com.rainbow.server.system.service.service.IRoleService;
import com.rainbow.server.system.service.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 *  @Description 角色业务层实现类
 *  @author liuhu
 *  @Date 2020/5/15 10:23
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleMapper roleMapper;

    private final IRoleMenuService roleMenuService;

    private final RoleMenuMapper roleMenuMapper;

    @Override
    public IPage<Role> page(QueryRequest queryRequest, Role role) {
        IPage<Role> roleIPage = null;
        try {
            Page<Role> page = new Page<>(queryRequest.getPageNum(),queryRequest.getPageSize());
            roleIPage = roleMapper.selectRolePage(page,role);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("删除失败");
        }
        return  roleIPage;
    }

    @Override
    public Role addRole(Role role) {
    try {
        // 新增角色
        roleMapper.insert(role);
        // 新增中间表
        saveRoleMenu(role,role.getMenuIds());
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("删除失败");
        }
        return role;
    }

    @Override
    public Role getRoleById(Long roleId) {
        Role role =null;
        try {
            // 新增角色
            role = roleMapper.selectById(roleId);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("通过ID得到角色");
        }
        return role;
    }

    @Override
    public void deleteRole(String roleIds) {
        try {
            String[] roleIdList = roleIds.split(StringPool.COMMA);
            // 删除角色
            roleMapper.deleteBatchIds(Arrays.asList(roleIdList));
            // 删除关系表
            roleMenuService.deleteRoleMenusByRoleId(roleIdList);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("通过ID得到角色");
        }
    }

    public void saveRoleMenu(Role role, String menuIds) {
        String[] menuIdList = menuIds.split(StringPool.COMMA);
        if(!ArrayUtils.isEmpty(menuIdList)){
            Arrays.asList(menuIdList).forEach(menuId->{
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getRoleId());
                roleMenuMapper.insert(roleMenu);
            });
        }
    }
}
