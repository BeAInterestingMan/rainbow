package com.rainbow.server.system.service.service.impl;


import com.rainbow.common.core.entity.system.RoleMenu;
import com.rainbow.server.system.service.service.IRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RoleMenuServiceImpl  implements IRoleMenuService {


    @Override
    public void deleteRoleMenusByRoleId(String[] roleIds) {

    }

    @Override
    public void deleteRoleMenusByMenuId(String[] menuIds) {

    }

    @Override
    public List<RoleMenu> getRoleMenusByRoleId(String roleId) {
        return null;
    }
}
